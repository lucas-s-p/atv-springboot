package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.AutorRepository;
import biblioteca.biblioteca.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroCrudPadraoService implements LivroCrudService {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto) {
        Livro livro = new Livro();
        preencherLivro(livro, livroPostPutDto);
        return paraResponseDto(livroRepository.save(livro));
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(this::paraResponseDto)
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(Long id) {
        return paraResponseDto(obterLivroExistente(id));
    }

    @Override
    public List<LivroResponseDto> buscarPorIsbn(String isbn) {
        return livroRepository.findByIsbnContainingIgnoreCase(isbn)
                .stream()
                .map(this::paraResponseDto)
                .toList();
    }

    @Override
    public List<LivroResponseDto> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::paraResponseDto)
                .toList();
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(id);
        preencherLivro(livro, livroPostPutDto);
        return paraResponseDto(livroRepository.save(livro));
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.delete(obterLivroExistente(id));
    }

    private void preencherLivro(Livro livro, LivroPostPutDto livroPostPutDto) {
        Autor autor = autorRepository.findById(livroPostPutDto.getAutorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));

        livro.setIsbn(livroPostPutDto.getIsbn());
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAno(livroPostPutDto.getAno());
        livro.setEdicao(livroPostPutDto.getEdicao());
        livro.setAutor(autor);
    }

    private LivroResponseDto paraResponseDto(Livro livro) {
        return LivroResponseDto.builder()
                .id(livro.getId())
                .isbn(livro.getIsbn())
                .titulo(livro.getTitulo())
                .ano(livro.getAno())
                .edicao(livro.getEdicao())
                .autorId(livro.getAutor().getId())
                .nomeAutor(livro.getAutor().getNomeAutor())
                .build();
    }

    private Livro obterLivroExistente(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }
}
