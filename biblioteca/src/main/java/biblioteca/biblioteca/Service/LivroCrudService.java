package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface LivroCrudService {

    LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto);

    List<LivroResponseDto> buscarTodosLivros();

    LivroResponseDto buscarLivro(Long id);

    List<LivroResponseDto> buscarLivrosTitulo(String titulo);

    List<LivroResponseDto> buscarLivrosIsbn(String isbn);

    List<LivroResponseDto> buscarLivrosTituloIsbn(String titulo, String isbn);

    LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto);

    void removerLivro(Long id);
}