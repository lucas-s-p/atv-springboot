package biblioteca.biblioteca.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.AutorRepository;
import biblioteca.biblioteca.Repository.LivroRepository;

@Service
public class LivroCrudPadraoService implements LivroCrudService {
   
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LivroRepository livroRepository;

    @Autowired 
    AutorRepository autorRepository;

    @Override 
    public LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto){
        Autor autor = autorRepository.findById(livroPostPutDto.getIdAutor())
                                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado")); 
        if (livroRepository.existsById(livroPostPutDto.getIsbn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ISBN JÁ EXISTENTE");
        }

        Livro livro = modelMapper.map(livroPostPutDto, Livro.class);
        livro.setAutor(autor);

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

    @Override 
    public LivroResponseDto atualizarLivro(String isbn, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(isbn);
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAno(livroPostPutDto.getAno());
        livro.setEdicao(livroPostPutDto.getEdicao());
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public LivroResponseDto buscarLivro(String isbn) {
        return modelMapper.map(obterLivroExistente(isbn), LivroResponseDto.class);
    } 

    @Override 
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                              .stream()
                              .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                              .toList();
    }

    @Override
    public List<LivroResponseDto> buscarLivroTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo)
                              .stream()
                              .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                              .toList();
    }
}
