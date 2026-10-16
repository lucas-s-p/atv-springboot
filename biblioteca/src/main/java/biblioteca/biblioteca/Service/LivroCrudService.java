package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface LivroCrudService {

    LivroResponseDto criarLivro(LivroPostPutDto livro);

    List<LivroResponseDto> buscarTodosLivros(String titulo, String isbn);

    LivroResponseDto buscarLivro(Long id);

    LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livro);

    void removerLivro(Long id);
}