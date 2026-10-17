package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostDto;
import biblioteca.biblioteca.Dto.LivroPutDto;
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
    public LivroResponseDto criarLivro(LivroPostDto livroPostDto) {
        if (livroRepository.existsById(livroPostDto.getIsbn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ISBN já cadastrado");
        }
        Autor autor = obterAutorExistente(livroPostDto.getAutorId());
        Livro livro = modelMapper.map(livroPostDto, Livro.class);
        livro.setAutor(autor);
        return paraResponse(livroRepository.save(livro));
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(this::paraResponse)
                .toList();
    }
 
    public List<LivroResponseDto> buscarLivrosIsbn(String isbn) {
        return livroRepository.findByIsbnContainingIgnoreCase(isbn)
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    public List<LivroResponseDto> buscarLivrosTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    public List<LivroResponseDto> buscarTodosLivrosIsbnTitulo(String isbn, String titulo) {
        return livroRepository.findByIsbnContainingIgnoreCaseAndTituloContainingIgnoreCase(isbn, titulo)
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(String isbn) {
        return paraResponse(obterLivroExistente(isbn));
    }

    @Override
    public LivroResponseDto atualizarLivro(String isbn, LivroPutDto livroPutDto) {
        Livro livro = obterLivroExistente(isbn);

        Autor autor = obterAutorExistente(livroPutDto.getAutorId());
        livro.setAno(livroPutDto.getAno());
        livro.setAutor(autor);
        livro.setEdicao(livroPutDto.getEdicao());
        livro.setTitulo(livroPutDto.getTitulo());
        
        return paraResponse(livroRepository.save(livro));
    }

    @Override
    public void removerLivro(String isbn) {
        livroRepository.delete(obterLivroExistente(isbn));
    }

    // isbn inexistente = 404
    private Livro obterLivroExistente(String isbn) {
        return livroRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    private Autor obterAutorExistente(Long autorId) {
        if (autorId == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado");
        }
        return autorRepository.findById(autorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }

    private LivroResponseDto paraResponse(Livro livro) {
        LivroResponseDto dto = modelMapper.map(livro, LivroResponseDto.class);
        if (livro.getAutor() != null) {
            dto.setAutorId(livro.getAutor().getId());
        }
        return dto;
    }
}