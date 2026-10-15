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
        return toResponse(livroRepository.save(livro));
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(Long id) {
        return toResponse(obterLivroExistente(id));
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(id);
        livro.setIsbn(livroPostPutDto.getIsbn());
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAutor(livroPostPutDto.getAutor());
        livro.setAno(livroPostPutDto.getAno());
        livro.setEdicao(livroPostPutDto.getEdicao());
        return toResponse(livroRepository.save(livro));
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.delete(obterLivroExistente(id));
    }

    @Override
    public List<LivroResponseDto> buscaParcial(String tituloParcial) {
        return livroRepository.findByTituloContainingIgnoreCase(tituloParcial)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private LivroResponseDto toResponse(Livro livro) {
        return modelMapper.map(livro, LivroResponseDto.class);
    }

    private Livro obterLivroExistente(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }
}