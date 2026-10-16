
package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.AutorPostPutDto;
import biblioteca.biblioteca.Dto.AutorResponseDto;
import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.LivroRepository;
import biblioteca.biblioteca.Repository.AutorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroCrudPadraoService implements LivroCrudService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LivroRepository livroRepository;

    @Autowired 
    AutorRepository autorRepository;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto dto) {
        Livro livro = modelMapper.map(dto, Livro.class);
       Autor autor = autorRepository.findById(dto.getAutorId())
        .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        livro.setAutor(autor);
        Livro livroSalvo = livroRepository.save(livro);
        return modelMapper.map(livroSalvo, LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(Long isbn) {
        return modelMapper.map(obterLivroExistente(isbn), LivroResponseDto.class);
    }


    @Override
    public void removerLivro(Long isbn) {
        livroRepository.delete(obterLivroExistente(isbn));
    }

    // id inexistente = 404
    private Livro obterLivroExistente(Long isbn) {
        return livroRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    @Override
    public LivroResponseDto atualizarLivro(Long isbn, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(isbn);
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAno(livroPostPutDto.getAno());
        livro.setEdicao(livroPostPutDto.getEdicao());
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarPorTitulo(String titulo) {
        List<Livro> livros = livroRepository.buscarPorTitulo(titulo);

        return livros.stream()
            .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
            .toList();
    }
}

