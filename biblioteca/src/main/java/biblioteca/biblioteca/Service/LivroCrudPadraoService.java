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
        Autor autor = obterAutorExistente(livroPostPutDto.getAutorId());

        Livro livro = modelMapper.map(livroPostPutDto, Livro.class);
        livro.setAutor(autor);

        Livro livroSalvo = livroRepository.save(livro);

        return converterParaResponseDto(livroSalvo);
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(this::converterParaResponseDto)
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(Long id) {
        return converterParaResponseDto(obterLivroExistente(id));
    }

    @Override
    public List<LivroResponseDto> buscarPorIsbn(String isbn) {
        return livroRepository.findByIsbnContainingIgnoreCase(isbn)
                .stream()
                .map(this::converterParaResponseDto)
                .toList();
    }

    @Override
    public List<LivroResponseDto> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::converterParaResponseDto)
                .toList();
    }

    @Override
    public LivroResponseDto atualizarLivro(
            Long id,
            LivroPostPutDto livroPostPutDto) {

        Livro livro = obterLivroExistente(id);
        Autor autor = obterAutorExistente(livroPostPutDto.getAutorId());

        livro.setIsbn(livroPostPutDto.getIsbn());
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAutor(autor);
        livro.setAno(livroPostPutDto.getAno());
        livro.setEdicao(livroPostPutDto.getEdicao());

        Livro livroAtualizado = livroRepository.save(livro);

        return converterParaResponseDto(livroAtualizado);
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.delete(obterLivroExistente(id));
    }

    private Livro obterLivroExistente(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Livro não encontrado"
                ));
    }

    private Autor obterAutorExistente(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Autor não encontrado"
                ));
    }

    private LivroResponseDto converterParaResponseDto(Livro livro) {
        LivroResponseDto dto = modelMapper.map(
                livro,
                LivroResponseDto.class
        );

        dto.setAutorId(livro.getAutor().getId());

        return dto;
    }
}