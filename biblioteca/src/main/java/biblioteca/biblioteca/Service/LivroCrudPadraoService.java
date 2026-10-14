package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.AutorRepository;
import biblioteca.biblioteca.Repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//ISBN, Título, Autor, Ano e Edição!!!!
@Service
public class LivroCrudPadraoService implements LivroCrudService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto) {
        Livro livro = Livro.builder()
                .isbn(livroPostPutDto.getIsbn())
                .titulo(livroPostPutDto.getTitulo())
                .autor(obterAutorExistente(livroPostPutDto.getAutorId()))
                .ano(livroPostPutDto.getAno())
                .edicao(livroPostPutDto.getEdicao())
                .build();
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    public List<LivroResponseDto> buscarLivrosPorIsbnOuTitulo(String isbn, String titulo) {
        boolean temIsbn = isbn != null && !isbn.isBlank();
        boolean temTitulo = titulo != null && !titulo.isBlank();

        List<Livro> livros;
        if (temIsbn && temTitulo) {
            livros = livroRepository.findByIsbnContainingAndTituloContainingIgnoreCase(isbn, titulo);
        } else if (temIsbn) {
            livros = livroRepository.findByIsbnContaining(isbn);
        } else if (temTitulo) {
            livros = livroRepository.findByTituloContainingIgnoreCase(titulo);
        } else {
            livros = livroRepository.findAll();
        }
        return livros
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(String isbn) {
        return modelMapper.map(obterLivroExistente(isbn), LivroResponseDto.class);
    }

    @Override
    public LivroResponseDto atualizarLivro(String isbn, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(isbn);
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAutor(obterAutorExistente(livroPostPutDto.getAutorId()));
        livro.setEdicao(livroPostPutDto.getEdicao());
        livro.setAno(livroPostPutDto.getAno());
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public void removerLivro(String isbn) {
        // delete(entidade) permite que o cascade da relação remova também os livros do autor
        livroRepository.delete(obterLivroExistente(isbn));
    }
    // isbn inexistente = 404
    private Livro obterLivroExistente(String isbn) {
        return livroRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    private Autor obterAutorExistente(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }
}