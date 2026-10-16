package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostDto;
import biblioteca.biblioteca.Dto.LivroPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface LivroCrudService {
    LivroResponseDto criarLivro(LivroPostDto livroPostDto);

    List<LivroResponseDto> buscarTodosLivros();

    LivroResponseDto buscarPorIsbn(String isbn);

    List<LivroResponseDto> buscarPorTitulo(String titulo);

    LivroResponseDto atualizarLivro(String isbn, LivroPutDto livroPutDto);

    void removerLivro(String isbn);
}