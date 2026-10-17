package biblioteca.biblioteca.Service;

import java.util.List;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

public interface LivroCrudService {

    LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto);

    List<LivroResponseDto> buscarTodosLivros();

    List<LivroResponseDto> buscarPorIsbn(String isbn);

    List<LivroResponseDto> buscarPorTitulo(String titulo);

    LivroResponseDto atualizarLivro(String isbn, LivroPostPutDto livroPostPutDto);

    void removerLivro(String isbn);
}
