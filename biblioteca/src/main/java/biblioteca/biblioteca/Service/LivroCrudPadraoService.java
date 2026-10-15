
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
public LivroResponseDto criarLivro(LivroPostPutDto dto) {
    // Verifica se o autor informado realmente existe.
    Autor autor = obterAutorExistente(dto.getAutorId());

    // Cria uma entidade nova, sem copiar propriedades automaticamente.
    Livro livro = new Livro();
    livro.setIsbn(dto.getIsbn());
    livro.setTitulo(dto.getTitulo());
    livro.setAutor(autor);
    livro.setAno(dto.getAno());
    livro.setEdicao(dto.getEdicao());

    System.out.println("ID do livro antes de salvar: " + livro.getId());
    // Como o ID não foi definido, o banco poderá gerá-lo.
    Livro livroSalvo = livroRepository.save(livro);

    return converterParaResponse(livroSalvo);
}


    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(Long id) {
        return converterParaResponse(obterLivroExistente(id));
    }

    @Override
    public List<LivroResponseDto> buscarLivrosPorIsbn(String isbn) {
        return livroRepository.findByIsbnContainingIgnoreCase(isbn)
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    @Override
    public List<LivroResponseDto> buscarLivrosPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto dto) {
        Livro livro = obterLivroExistente(id);
        Autor autor = obterAutorExistente(dto.getAutorId());

        livro.setIsbn(dto.getIsbn());
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(autor);
        livro.setAno(dto.getAno());
        livro.setEdicao(dto.getEdicao());

        Livro livroSalvo = livroRepository.save(livro);
        return converterParaResponse(livroSalvo);
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.delete(obterLivroExistente(id));
    }

    private Livro obterLivroExistente(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    private Autor obterAutorExistente(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }

    private LivroResponseDto converterParaResponse(Livro livro) {
        LivroResponseDto dto = modelMapper.map(livro, LivroResponseDto.class);
        dto.setAutorId(livro.getAutor().getId());
        return dto;
    }
}
