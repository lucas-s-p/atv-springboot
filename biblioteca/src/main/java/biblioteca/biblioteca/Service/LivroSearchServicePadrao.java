package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroResponseDto;

import biblioteca.biblioteca.Repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroSearchServicePadrao implements LivroSearchService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public List<LivroResponseDto> buscar(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            return List.of();
        }

        return livroRepository
                .findByTituloLivroContainingIgnoreCaseOrIsbnLivroContainingIgnoreCase(termo, termo)
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }
}
