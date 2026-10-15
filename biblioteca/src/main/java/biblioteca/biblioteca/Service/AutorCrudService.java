package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.AutorPostPutDto;
import biblioteca.biblioteca.Dto.AutorResponseDto;

import java.util.List;

public interface AutorCrudService {

    AutorResponseDto criarAutor(AutorPostPutDto autorPostPutDto);

    List<AutorResponseDto> buscarTodosAutores();

    AutorResponseDto buscarAutor(Long id);

    AutorResponseDto atualizarAutor(Long id, AutorPostPutDto autorPostPutDto);

    void removerAutor(Long id);
}
