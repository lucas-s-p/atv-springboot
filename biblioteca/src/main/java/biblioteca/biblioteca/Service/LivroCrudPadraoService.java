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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class LivroCrudPadraoService implements LivroCrudService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto) {
        if (livroRepository.existsByIsbn(livroPostPutDto.getIsbn())) {
            throw isbnDuplicado();
        }
        Livro livro = Livro.builder()
                .isbn(livroPostPutDto.getIsbn())
                .titulo(livroPostPutDto.getTitulo())
                .ano(livroPostPutDto.getAno())
                .edicao(livroPostPutDto.getEdicao())
                .autor(obterAutorExistente(livroPostPutDto.getAutorId()))
                .build();
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LivroResponseDto> buscarLivros(String isbn, String titulo) {
        boolean temIsbn = isbn != null && !isbn.isBlank();
        boolean temTitulo = titulo != null && !titulo.isBlank();

        List<Livro> livros;
        if (temIsbn && temTitulo) {
            livros = livroRepository.findByIsbnContainingIgnoreCaseAndTituloContainingIgnoreCase(isbn.trim(), titulo.trim());
        } else if (temIsbn) {
            livros = livroRepository.findByIsbnContainingIgnoreCase(isbn.trim());
        } else if (temTitulo) {
            livros = livroRepository.findByTituloContainingIgnoreCase(titulo.trim());
        } else {
            livros = livroRepository.findAll();
        }

        return livros.stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LivroResponseDto buscarLivro(Long id) {
        return modelMapper.map(obterLivroExistente(id), LivroResponseDto.class);
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(id);
        if (livroRepository.existsByIsbnAndIdNot(livroPostPutDto.getIsbn(), id)) {
            throw isbnDuplicado();
        }
        livro.setIsbn(livroPostPutDto.getIsbn());
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAno(livroPostPutDto.getAno());
        livro.setEdicao(livroPostPutDto.getEdicao());
        livro.setAutor(obterAutorExistente(livroPostPutDto.getAutorId()));
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.delete(obterLivroExistente(id));
    }

    private Livro obterLivroExistente(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    private Autor obterAutorExistente(Long autorId) {
        return autorRepository.findById(autorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }

    private ResponseStatusException isbnDuplicado() {
        return new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um livro com este ISBN");
    }
}