package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Livro;
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

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto) {
        Livro livro = modelMapper.map(livroPostPutDto, Livro.class);
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    public List<LivroResponseDto> buscarTodosLivrosIsbn(String isbn) {
        return livroRepository.findByIsbnContaining(isbn)
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    public List<LivroResponseDto> buscarTodosLivrosTitulo(String titulo) {
        return livroRepository.findByTituloContaining(titulo)
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    public List<LivroResponseDto> buscarTodosLivrosIsbnTitulo(String isbn, String titulo) {
        return livroRepository.findByIsbnContainingAndTituloContaining(isbn, titulo)
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(String isbn) {
        return modelMapper.map(obterLivroExistente(isbn), LivroResponseDto.class);
    }

    @Override
    public LivroResponseDto atualizarLivro(String isbn, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(isbn);
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAno(livroPostPutDto.getAno());
        livro.setEdicao(livroPostPutDto.getEdicao());
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public void removerLivro(String isbn) {
        // delete(entidade) permite que o cascade da relação remova também os livros do livro
        livroRepository.delete(obterLivroExistente(isbn));
    }

    // isbn inexistente = 404
    private Livro obterLivroExistente(String isbn) {
        return livroRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }
}
