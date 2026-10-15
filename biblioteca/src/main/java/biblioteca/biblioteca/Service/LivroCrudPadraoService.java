package biblioteca.biblioteca.Service;

import java.util.*;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Repository.LivroRepository;
import biblioteca.biblioteca.Dto.AutorResponseDto;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Repository.AutorRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LivroCrudPadraoService implements LivroCrudService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto dto) {
        Livro livro = Livro.builder()
                .isbn(dto.getIsbn())
                .titulo(dto.getTitulo())
                .ano(dto.getAno())
                .edicao(dto.getEdicao())
                .autor(obterAutorExistente(dto.getAutorId()))
                .build();
        return paraDto(livroRepository.save(livro));
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll().stream().map(this::paraDto).toList();
    }

    @Override
    public LivroResponseDto buscarLivro(Long id) {
        return paraDto(obterLivroExistente(id));
    }

    @Override
    public List<LivroResponseDto> buscarPorIsbn(String isbn) {
        return livroRepository.findByIsbn(isbn)
                .stream().map(this::paraDto).toList();
    }

    @Override
    public List<LivroResponseDto> buscarPorTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo)
                .stream().map(this::paraDto).toList();
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto dto) {
        Livro livro = obterLivroExistente(id);
        livro.setIsbn(dto.getIsbn());
        livro.setTitulo(dto.getTitulo());
        livro.setAno(dto.getAno());
        livro.setEdicao(dto.getEdicao());
        livro.setAutor(obterAutorExistente(dto.getAutorId()));
        return paraDto(livroRepository.save(livro));
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

    private LivroResponseDto paraDto(Livro livro) {
        return LivroResponseDto.builder()
                .id(livro.getId())
                .isbn(livro.getIsbn())
                .titulo(livro.getTitulo())
                .ano(livro.getAno())
                .edicao(livro.getEdicao())
                .autor(modelMapper.map(livro.getAutor(), AutorResponseDto.class))
                .build();
    }
}