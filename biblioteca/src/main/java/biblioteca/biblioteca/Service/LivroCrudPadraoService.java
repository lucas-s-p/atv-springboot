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

import java.util.ArrayList;
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
        if (livroRepository.existsById(livroPostPutDto.getIsbn())){
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Já Existe um livro com o ISBN:" + livroPostPutDto.getIsbn()
            );
        }

        Autor autor = obterAutorExistente(livroPostPutDto.getAutor_id());

        Livro livro = modelMapper.map(livroPostPutDto,  Livro.class);
        livro.setAutor(autor);
        
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarTodosOsLivros() {
        return livroRepository.findAll()
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    public List<LivroResponseDto> buscarLivroPorTitulo(String titulo) {
        List<Livro> todosOsLivros = livroRepository.findAll();
        List<LivroResponseDto> resultado = new ArrayList<>();

        for (Livro livro : todosOsLivros) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                LivroResponseDto lrdto = modelMapper.map(livro, LivroResponseDto.class);
                resultado.add(lrdto);
            }
        }

        return resultado;
    }

    @Override
    public LivroResponseDto buscarLivroPorIsbn(String isbn) {
        Livro livro = livroRepository.findById(isbn).orElseThrow(
            () -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Livro com ISBN " + isbn + " não encontrado"
        ));

        return modelMapper.map(livro, LivroResponseDto.class);
    }

    @Override
    public LivroResponseDto atualizarLivro(String isbn, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(isbn);
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAno(livroPostPutDto.getAno());
        livro.setAutor(obterAutorExistente(livroPostPutDto.getAutor_id()));
        livro.setEdicao(livroPostPutDto.getEdicao());
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public void removerLivro(String isbn) {
        livroRepository.delete(obterLivroExistente(isbn));
    }

    // id inexistente = 404
    private Livro obterLivroExistente(String isbn) {
        return livroRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    private Autor obterAutorExistente(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }
}