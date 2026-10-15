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
        Livro livro = Livro.builder()
                .isbn(livroPostPutDto.getIsbn())
                .titulo(livroPostPutDto.getTitulo())
                .autor(obterAutorExistente(livroPostPutDto.getAutorId()))
                .ano(livroPostPutDto.getAno())
                .edicao(livroPostPutDto.getEdicao())
                .build();
        return converterParaResponse(livroRepository.save(livro));
    }

    @Override
    public List<LivroResponseDto> buscarLivros(String isbn, String titulo) {
        String isbnBusca = normalizarBusca(isbn);
        String tituloBusca = normalizarBusca(titulo);
        List<Livro> livros;

        if (isbnBusca == null && tituloBusca == null) {
            livros = livroRepository.findAll();
        } else if (isbnBusca == null) {
            livros = livroRepository.findByTituloContainingIgnoreCase(tituloBusca);
        } else if (tituloBusca == null) {
            livros = livroRepository.findByIsbnContainingIgnoreCase(isbnBusca);
        } else {
            livros = livroRepository.findByIsbnContainingIgnoreCaseOrTituloContainingIgnoreCase(
                    isbnBusca, tituloBusca);
        }

        return livros.stream().map(this::converterParaResponse).toList();
    }

    @Override
    public LivroResponseDto buscarLivro(Long id) {
        return converterParaResponse(obterLivroExistente(id));
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(id);
        livro.setIsbn(livroPostPutDto.getIsbn());
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAutor(obterAutorExistente(livroPostPutDto.getAutorId()));
        livro.setAno(livroPostPutDto.getAno());
        livro.setEdicao(livroPostPutDto.getEdicao());
        return converterParaResponse(livroRepository.save(livro));
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

    private String normalizarBusca(String busca) {
        return busca == null || busca.isBlank() ? null : busca.trim();
    }

    private LivroResponseDto converterParaResponse(Livro livro) {
        return LivroResponseDto.builder()
                .id(livro.getId())
                .isbn(livro.getIsbn())
                .titulo(livro.getTitulo())
                .autorId(livro.getAutor().getId())
                .ano(livro.getAno())
                .edicao(livro.getEdicao())
                .build();
    }
}
