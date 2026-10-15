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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class LivroCrudPadraoService implements LivroCrudService {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto dto) {
        if (livroRepository.existsByIsbn(dto.getIsbn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já tem livro com esse ISBN");
        }
        Livro livro = new Livro();
        preencherLivro(livro, dto);
        return converterParaDto(livroRepository.save(livro));
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(this::converterParaDto)
                .toList();
    }

    @Override
    public List<LivroResponseDto> buscarLivros(String isbn, String titulo) {
        List<Livro> livros;
        if (isbn != null && !isbn.isBlank()) {
            livros = livroRepository.findByIsbnContainingIgnoreCase(isbn);
        } else if (titulo != null && !titulo.isBlank()) {
            livros = livroRepository.findByTituloContainingIgnoreCase(titulo);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe o isbn ou o titulo");
        }
        return livros.stream()
                .map(this::converterParaDto)
                .toList();
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto dto) {
        Livro livro = obterLivroExistente(id);
        if (livroRepository.existsByIsbnAndIdNot(dto.getIsbn(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já tem livro com esse ISBN");
        }
        preencherLivro(livro, dto);
        return converterParaDto(livroRepository.save(livro));
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.delete(obterLivroExistente(id));
    }

    private void preencherLivro(Livro livro, LivroPostPutDto dto) {
        livro.setIsbn(dto.getIsbn());
        livro.setTitulo(dto.getTitulo());
        livro.setAno(dto.getAno());
        livro.setEdicao(dto.getEdicao());
        livro.setAutor(obterAutorExistente(dto.getAutorId()));
    }

    private Livro obterLivroExistente(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro nao encontrado"));
    }

    private Autor obterAutorExistente(Long autorId) {
        return autorRepository.findById(autorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor nao encontrado"));
    }

    private LivroResponseDto converterParaDto(Livro livro) {
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
}
