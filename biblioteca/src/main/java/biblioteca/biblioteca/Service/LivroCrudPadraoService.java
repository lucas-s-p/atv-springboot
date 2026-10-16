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
    LivroRepository livroRepository;

    @Autowired  
    AutorRepository autorRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto) {
        if (livroRepository.existsById(livroPostPutDto.getIsbn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ISBN já existente");
        } 
        
        Livro livro = Livro.builder()
            .isbn(livroPostPutDto.getIsbn())
            .titulo(livroPostPutDto.getTitulo())
            .autor(obterAutorExistente(livroPostPutDto.getAutorId()))
            .ano(livroPostPutDto.getAno())
            .edicao(livroPostPutDto.getEdicao())
            .build();
        
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
    public List<LivroResponseDto> buscarPorIsbnOuTitulo(String termo) {
        String isbn = termo.matches("[\\d\\-\\s]+") ? termo.replaceAll("\\D", "") : "";
        return livroRepository.buscarPorIsbnOuTitulo(isbn, termo)
            .stream()
            .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
            .toList();
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(id);
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAutor(obterAutorExistente(livroPostPutDto.getAutorId()));
        livro.setAno(livroPostPutDto.getAno());
        livro.setEdicao(livroPostPutDto.getEdicao());
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.delete(obterLivroExistente(id));  
    }
    
    private Livro obterLivroExistente(Long id) {
        return livroRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado por id"));
    }

    private Autor obterAutorExistente(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }
}
