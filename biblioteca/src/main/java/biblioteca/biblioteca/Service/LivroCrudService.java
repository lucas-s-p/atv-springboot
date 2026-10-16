package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.AutorPostPutDto;
import biblioteca.biblioteca.Dto.AutorResponseDto;
import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface LivroCrudService {

    List<LivroResponseDto> buscarTodosLivros();

    LivroResponseDto buscarLivro(Long isbn);

    LivroResponseDto atualizarLivro(Long isbn, LivroPostPutDto livroPostPutDto);

    void removerLivro(Long isbn);

    LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto);
    
    List<LivroResponseDto> buscarPorTitulo(String titulo);
}
