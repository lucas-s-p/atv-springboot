package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface LivroCrudService {

    LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto);

    List<LivroResponseDto> buscarTodosLivros();

    LivroResponseDto buscarLivro(Long id);

    LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto);

    void removerLivro(Long id);

    List<LivroResponseDto> buscaParcial(String tituloParcial);
    
}