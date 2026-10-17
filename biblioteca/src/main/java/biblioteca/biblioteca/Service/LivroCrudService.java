package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface LivroCrudService {

    LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto);

    List<LivroResponseDto> buscarTodosLivros();

    List<LivroResponseDto> buscarTodosLivrosIsbn(String isbn);

    List<LivroResponseDto> buscarTodosLivrosTitulo(String titulo);

    List<LivroResponseDto> buscarTodosLivrosIsbnTitulo(String isbn, String titulo);

    LivroResponseDto buscarLivro(String isbn);

    LivroResponseDto atualizarLivro(String isbn, LivroPostPutDto livroPostPutDto);

    void removerLivro(String isbn);
}
