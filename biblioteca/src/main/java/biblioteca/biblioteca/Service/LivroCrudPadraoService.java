package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostDto;
import biblioteca.biblioteca.Dto.LivroPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Repository.AutorRepository;
import biblioteca.biblioteca.Repository.LivroRepository;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Model.Livro;
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

        Long idAutor = livroPostDto.getIdAutor();
        Autor autor = autorCadastrado(idAutor);

        Livro livro = modelMapper.map(livroPostDto, Livro.class);
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
    public LivroResponseDto buscarPorIsbn(String isbn) {
        return modelMapper.map(obterLivroExistente(isbn), LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarPorTitulo(String titulo) {
        return livroRepository.buscarPorTitulo(titulo)
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    public LivroResponseDto atualizarLivro(String isbn, LivroPutDto livroPutDto) {
        Livro livro = obterLivroExistente(isbn);
        livro.setTitulo(livroPutDto.getTitulo());
        livro.setAno(livroPutDto.getAno());
        livro.setEdicao(livroPutDto.getEdicao());
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public void removerLivro(String isbn) {
        livroRepository.delete(obterLivroExistente(isbn));
    }

    private Livro obterLivroExistente(String isbn) {
        return livroRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    private Autor autorCadastrado(Long idAutor) {
        return autorRepository.findById(idAutor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }
}