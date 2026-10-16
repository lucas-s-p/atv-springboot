package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface LivroSearchService {
    List<LivroResponseDto> buscar(String termo);
}
