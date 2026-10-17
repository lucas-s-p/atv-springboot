package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostDto;
import biblioteca.biblioteca.Dto.LivroPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface LivroCrudService {

    LivroResponseDto criarLivro(LivroPostDto livroPostDto);

    List<LivroResponseDto> buscarTodosLivros();

    LivroResponseDto buscarLivro(String isbn);

    List<LivroResponseDto> buscaPorTitulo(String titulo);

    List<LivroResponseDto> buscaPorIsbn(String isbn);

    LivroResponseDto atualizarLivro(String isbn, LivroPutDto livroPutDto);

    void removerLivro(String isbn);
}