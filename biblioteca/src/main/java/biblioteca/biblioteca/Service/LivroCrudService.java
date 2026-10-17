package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface LivroCrudService {

    LivroResponseDto criarLivro(LivroPostPutDto LivroPostPutDto);

    List<LivroResponseDto> buscarTodosLivros();

    LivroResponseDto buscarLivroPorISBN(String isbn);

    List<LivroResponseDto> buscarLivroPorTitulo(String titulo);

    LivroResponseDto atualizarLivro(Long id, LivroPostPutDto LivroPostPutDto);

    void removerLivro(Long id);
}
