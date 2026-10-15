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
    public LivroResponseDto criarLivro(LivroPostPutDto dto) {
        if (livroRepository.existsByIsbn(dto.getIsbn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ISBN já cadastrado");
        }
        Autor autor = obterAutorExistente(dto.getAutorId());

        Livro livro = Livro.builder()
                .isbn(dto.getIsbn())
                .titulo(dto.getTitulo())
                .ano(dto.getAno())
                .edicao(dto.getEdicao())
                .autor(autor)
                .build();

        return converter(livroRepository.save(livro));
    }

    @Override
    public List<LivroResponseDto> buscarLivros(String isbn, String titulo) {
        List<Livro> livros;
        if (isbn != null && !isbn.isBlank()) {
            livros = livroRepository.findByIsbnContainingIgnoreCase(isbn);
        } else if (titulo != null && !titulo.isBlank()) {
            livros = livroRepository.findByTituloContainingIgnoreCase(titulo);
        } else {
            livros = livroRepository.findAll();
        }
        return livros.stream().map(this::converter).toList();
    }

    @Override
    public LivroResponseDto buscarLivro(Long id) {
        return converter(obterLivroExistente(id));
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto dto) {
        Livro livro = obterLivroExistente(id);

        if (livroRepository.existsByIsbnAndIdNot(dto.getIsbn(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ISBN já cadastrado");
        }
        Autor autor = obterAutorExistente(dto.getAutorId());

        livro.setIsbn(dto.getIsbn());
        livro.setTitulo(dto.getTitulo());
        livro.setAno(dto.getAno());
        livro.setEdicao(dto.getEdicao());
        livro.setAutor(autor);

        return converter(livroRepository.save(livro));
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

    private LivroResponseDto converter(Livro livro) {
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