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
    private ModelMapper modelMapper;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto) {
        Autor autor = buscarAutorExistente(livroPostPutDto.getAutor());

        Livro livro = modelMapper.map(livroPostPutDto, Livro.class);
        livro.setAutor(autor);

        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(Long id) {
        return modelMapper.map(obterLivroExistente(id), LivroResponseDto.class);
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(id);
        Autor autor = buscarAutorExistente(livroPostPutDto.getAutor());

        livro.setTituloLivro(livroPostPutDto.getTituloLivro());
        livro.setIsbnLivro(livroPostPutDto.getIsbnLivro());
        livro.setAnoPublicacao(livroPostPutDto.getAnoPublicacao());
        livro.setEdicaoLivro(livroPostPutDto.getEdicaoLivro());
        livro.setAutor(autor);

        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.delete(obterLivroExistente(id));
    }

    private Livro obterLivroExistente(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    private Autor buscarAutorExistente(Long autorId) {
        return autorRepository.findById(autorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Autor não encontrado com ID: " + autorId));
    }
}