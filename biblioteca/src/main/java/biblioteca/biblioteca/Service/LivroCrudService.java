package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.AutorRepository;
import biblioteca.biblioteca.Repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import biblioteca.biblioteca.Dto.AutorResponseDto;

import java.util.List;

@Service
public class LivroCrudService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroCrudService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @Transactional
    public LivroResponseDto criarLivro(LivroPostPutDto dto) {
        if (livroRepository.existsByIsbn(dto.getIsbn())) {
            throw new RuntimeException("Já existe um livro cadastrado com este ISBN.");
        }

        Autor autor = autorRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado."));

        Livro livro = Livro.builder()
                .isbn(dto.getIsbn())
                .titulo(dto.getTitulo())
                .ano(dto.getAno())
                .edicao(dto.getEdicao())
                .autor(autor)
                .build();

        Livro salvo = livroRepository.save(livro);
        return toResponseDto(salvo);
    }

    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public LivroResponseDto buscarLivro(String isbn) {
        Livro livro = livroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado."));
        return toResponseDto(livro);
    }

    public List<LivroResponseDto> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public List<LivroResponseDto> buscarLivros(String termo) {
        if (termo == null || termo.isBlank()) {
            return buscarTodosLivros();
        }
        return livroRepository.findByIsbnContainingIgnoreCaseOrTituloContainingIgnoreCase(termo, termo)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Transactional
    public LivroResponseDto atualizarLivro(String isbn, LivroPostPutDto dto) {
        Livro livro = livroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado."));

        if (!livro.getAutor().getId().equals(dto.getAutorId())) {
            Autor novoAutor = autorRepository.findById(dto.getAutorId())
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado."));
            livro.setAutor(novoAutor);
        }

        livro.setIsbn(dto.getIsbn());
        livro.setTitulo(dto.getTitulo());
        livro.setAno(dto.getAno());
        livro.setEdicao(dto.getEdicao());

        Livro atualizado = livroRepository.save(livro);
        return toResponseDto(atualizado);
    }

    @Transactional
    public void removerLivro(String isbn) {
        Livro livro = livroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado."));
        livroRepository.delete(livro);
    }

private LivroResponseDto toResponseDto(Livro livro) {
    // 1. Converte a entidade Autor para o DTO de resposta do Autor
    AutorResponseDto autorDto = null;
    if (livro.getAutor() != null) {
        autorDto = AutorResponseDto.builder()
                .id(livro.getAutor().getId())
                .nomeAutor(livro.getAutor().getNomeAutor())
                .nacionalidade(livro.getAutor().getNacionalidade())
                .build();
    }

    // 2. Constrói a DTO do Livro com a autorDto convertida
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