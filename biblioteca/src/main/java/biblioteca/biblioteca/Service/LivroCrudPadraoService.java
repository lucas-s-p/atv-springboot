package biblioteca.biblioteca.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Repository.AutorRepository;
import biblioteca.biblioteca.Repository.LivroRepository;

@Service
public class LivroCrudPadraoService implements LivroCrudService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto LivroPostPutDto) {
        Livro livro = new Livro();
        livro.setIsbn(LivroPostPutDto.getIsbn());
        livro.setTitulo(LivroPostPutDto.getTitulo());
        livro.setAutor(obterAutorExistente(LivroPostPutDto.getAutorId()));
        livro.setAno(LivroPostPutDto.getAno());
        livro.setEdicao(LivroPostPutDto.getEdicao());
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository
                .findAll()
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivroPorISBN(String isbn) {
        Livro livro = livroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
        return modelMapper.map(livro, LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarLivroPorTitulo(String titulo) {
        return livroRepository
                .findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto LivroPostPutDto) {
        Livro livro = obterLivroExistente(id);
        livro.setIsbn(LivroPostPutDto.getIsbn());
        livro.setTitulo(LivroPostPutDto.getTitulo());
        livro.setAutor(obterAutorExistente(LivroPostPutDto.getAutorId()));
        livro.setAno(LivroPostPutDto.getAno());
        livro.setEdicao(LivroPostPutDto.getEdicao());

        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.delete(obterLivroExistente(id));
    }

    private Autor obterAutorExistente(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }

    private Livro obterLivroExistente(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }
}
