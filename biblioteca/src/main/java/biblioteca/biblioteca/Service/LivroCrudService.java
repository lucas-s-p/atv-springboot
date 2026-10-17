package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostDto;
import biblioteca.biblioteca.Dto.LivroPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface LivroCrudService {

    LivroResponseDto criarLivro(LivroPostDto livroPostDto); 

    List<LivroResponseDto> buscarTodosLivros();

    List<LivroResponseDto> buscarLivrosIsbn(String isbn);
    
    List<LivroResponseDto> buscarLivrosTitulo(String titulo);

    List<LivroResponseDto> buscarTodosLivrosIsbnTitulo(String isbn, String Titulo);

    LivroResponseDto buscarLivro(String isbn);

    LivroResponseDto atualizarLivro(String isbn, LivroPutDto livroPutDto);

    void removerLivro(String isbn);
}
