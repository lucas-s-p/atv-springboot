package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface LivroCrudService {
    
    LivroResponseDto criarLivro(LivroPostPutDto dto);
    
    List<LivroResponseDto> buscarTodosLivros();
    
    List<LivroResponseDto> buscarPorIsbn(String isbn);
    
    List<LivroResponseDto> buscarPorTitulo(String titulo);
    
    LivroResponseDto atualizarLivro(Long id, LivroPostPutDto dto);
    
    void removerLivro(Long id);
}