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
        Autor autor = obterAutorExistente(livroPostPutDto.getIdAutor());
        Livro livro = modelMapper.map(livroPostPutDto, Livro.class);
        livro.setId(null);
        livro.setAutor(autor);
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(Long id) {
        return toResponseDto(buscarLivroExistente(id));
    }

    @Override
    public List<LivroResponseDto> buscarLivrosTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public List<LivroResponseDto> buscarLivrosIsbn(String isbn) {
        return livroRepository.findByIsbnContainingIgnoreCase(isbn)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public List<LivroResponseDto> buscarLivrosTituloIsbn(String titulo, String isbn) {
        return livroRepository.findByTituloContainingIgnoreCaseAndIsbnContainingIgnoreCase(titulo, isbn)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto) {
        Livro livro = buscarLivroExistente(id);
        livro.setAno(livroPostPutDto.getAno());
        livro.setEdicao(livroPostPutDto.getEdicao());
        livro.setAutor(obterAutorExistente(livroPostPutDto.getIdAutor()));
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setIsbn(livroPostPutDto.getIsbn());
        return toResponseDto(livroRepository.save(livro));
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.delete(buscarLivroExistente(id));
    }

    private Livro buscarLivroExistente(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    // id inexistente = 404
    private Autor obterAutorExistente(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }

    private LivroResponseDto toResponseDto(Livro livro) {
        LivroResponseDto lrdto = modelMapper.map(livro, LivroResponseDto.class);
        lrdto.setIdAutor(livro.getAutor().getId());
        return lrdto;
    }
}