package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.AutorResponseDto;
import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.AutorRepository;
import biblioteca.biblioteca.Repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        Autor autor = obterAutorExistente(livroPostPutDto.getAutorId());
        validarIsbnDisponivel(livroPostPutDto.getIsbn(), null);
        Livro livro = Livro.builder()
                .isbn(livroPostPutDto.getIsbn())
                .titulo(livroPostPutDto.getTitulo())
                .ano(livroPostPutDto.getAno())
                .edicao(livroPostPutDto.getEdicao())
                .autor(autor)
                .build();
        try {
            return toResponseDto(livroRepository.save(livro));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ISBN já cadastrado");
        }
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros(String titulo, String isbn) {
        boolean temTitulo = titulo != null && !titulo.isBlank();
        boolean temIsbn = isbn != null && !isbn.isBlank();
        List<Livro> livros;
        if (temTitulo && temIsbn) {
            livros = livroRepository.findByTituloContainingIgnoreCaseAndIsbnContainingIgnoreCase(titulo, isbn);
        } else if (temTitulo) {
            livros = livroRepository.findByTituloContainingIgnoreCase(titulo);
        } else if (temIsbn) {
            livros = livroRepository.findByIsbnContainingIgnoreCase(isbn);
        } else {
            livros = livroRepository.findAll();
        }
        return livros.stream().map(this::toResponseDto).toList();
    }

    @Override
    public LivroResponseDto buscarLivro(Long id) {
        return toResponseDto(obterLivroExistente(id));
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(id);
        Autor autor = obterAutorExistente(livroPostPutDto.getAutorId());
        validarIsbnDisponivel(livroPostPutDto.getIsbn(), id);
        livro.setIsbn(livroPostPutDto.getIsbn());
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAno(livroPostPutDto.getAno());
        livro.setEdicao(livroPostPutDto.getEdicao());
        livro.setAutor(autor);
        try {
            return toResponseDto(livroRepository.save(livro));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ISBN já cadastrado");
        }
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.delete(obterLivroExistente(id));
    }

    private Livro obterLivroExistente(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    private Autor obterAutorExistente(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }

    private void validarIsbnDisponivel(String isbn, Long idAtual) {
        boolean duplicado = idAtual == null
                ? livroRepository.existsByIsbn(isbn)
                : livroRepository.existsByIsbnAndIdNot(isbn, idAtual);
        if (duplicado) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ISBN já cadastrado");
        }
    }

    private LivroResponseDto toResponseDto(Livro livro) {
        Autor autor = livro.getAutor();
        AutorResponseDto autorDto = AutorResponseDto.builder()
                .id(autor.getId())
                .nomeAutor(autor.getNomeAutor())
                .nacionalidade(autor.getNacionalidade())
                .build();
        return LivroResponseDto.builder()
                .id(livro.getId())
                .isbn(livro.getIsbn())
                .titulo(livro.getTitulo())
                .ano(livro.getAno())
                .edicao(livro.getEdicao())
                .autor(autorDto)
                .build();
    }
}
