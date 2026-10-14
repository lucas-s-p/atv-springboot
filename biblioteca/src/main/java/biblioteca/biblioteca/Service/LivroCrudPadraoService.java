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
        Livro livro = modelMapper.map(livroPostPutDto, Livro.class);
        
        vincularAutorCasoInformado(livro, livroPostPutDto.getAutorId());

        return paraResponseDto(livroRepository.save(livro));
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros(String titulo) {
        List<Livro> livros;

        if (titulo != null && !titulo.isBlank()) {
            livros = livroRepository.findByNomeLivroContainingIgnoreCase(titulo);
        } else {
            livros = livroRepository.findAll();
        }
        return livros.stream()
                .map(this::paraResponseDto)
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(Long isbn) {
        return paraResponseDto(obterLivroExistente(isbn));
    }

    @Override
    public List<LivroResponseDto> buscarLivrosPorTitulo(String titulo) {
        List<Livro> livros = livroRepository.findByNomeLivroContainingIgnoreCase(titulo);
        
        if (livros.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum livro encontrado com o título informado");
        }
        
        return livros.stream().map(this::paraResponseDto).toList();
    }

    @Override
    public LivroResponseDto atualizarLivro(Long isbn, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(isbn);
        
        livro.setNomeLivro(livroPostPutDto.getNomeLivro());
        livro.setAno(livroPostPutDto.getAno());
        livro.setEdicao(livroPostPutDto.getEdicao());
        
        vincularAutorCasoInformado(livro, livroPostPutDto.getAutorId());

        return paraResponseDto(livroRepository.save(livro));
    }

    @Override
    public void removerLivro(Long isbn) {
        livroRepository.delete(obterLivroExistente(isbn));
    }

    private Livro obterLivroExistente(Long isbn) {
        return livroRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    private void vincularAutorCasoInformado(Livro livro, Long autorId) {
        if (autorId != null) {
            Autor autor = autorRepository.findById(autorId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor associado não encontrado"));
            livro.setAutor(autor);
        } else {
            livro.setAutor(null);
        }
    }

    private LivroResponseDto paraResponseDto(Livro livro) {
        LivroResponseDto dto = modelMapper.map(livro, LivroResponseDto.class);
        if (livro.getAutor() != null) {
            dto.setNomeAutor(livro.getAutor().getNomeAutor());
        }
        return dto;
    }
}
