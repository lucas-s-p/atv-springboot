package biblioteca.biblioteca.Service;

import java.util.List;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

public interface LivroCrudService {
    
    LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto);

    void removerLivro(String isbn);

    LivroResponseDto atualizarLivro(String isbn, LivroPostPutDto livroPostPutDto);

    List<LivroResponseDto> buscarTodosLivros();

    LivroResponseDto buscarLivro(String isbn);

    List<LivroResponseDto> buscarLivroTitulo(String titulo);

}
