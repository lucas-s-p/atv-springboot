package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface LivroCrudService {

    LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto);

    List<LivroResponseDto> buscarTodosOsLivros();

    List<LivroResponseDto> buscarLivroPorTitulo(String titulo);

    LivroResponseDto buscarLivroPorIsbn(String isbn);
    
    LivroResponseDto atualizarLivro(String isbn, LivroPostPutDto livroPostPutDto);

    void removerLivro(String isbn);
}