package biblioteca.biblioteca.Service;

import java.util.List;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroReponseDTO;

public interface LivroCrudService {
    LivroReponseDTO salvar(LivroPostPutDto dto);
   
    List<LivroReponseDTO> listarTodos(); 
   
    LivroReponseDTO buscarId(Long id); 
   
    LivroReponseDTO atualizar(Long id, LivroPostPutDto dto); 
   
    void deletar(Long id); 
   
    List<LivroReponseDTO> buscarPortitulo(String titulo); 
   
    List<LivroReponseDTO> buscarPorIsbn(String isbn);
}
