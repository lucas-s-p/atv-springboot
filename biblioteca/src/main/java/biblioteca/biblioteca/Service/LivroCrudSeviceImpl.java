package biblioteca.biblioteca.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroReponseDTO;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.AutorRepository;
import biblioteca.biblioteca.Repository.LivroRepository;
import org.springframework.data.domain.Example;

public class LivroCrudSeviceImpl  implements  LivroCrudService{

    @Autowired 
    private  LivroRepository livroRepository; 

    @Autowired 
    private AutorRepository autorRepository; 


    @Override
    public LivroReponseDTO salvar(LivroPostPutDto dto) {
        Autor autor = autorRepository.findById(dto.getIdAutor())
        .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID"));
        
        Livro livro = Livro.builder()
        .isbn(dto.getIsbn())
        .titulo(dto.getTitulo())
        .ano(dto.getAno())
        .edicao(dto.getEdicao())
        .autor(autor)
        .build(); 

        return  paraDTO(livroRepository.save(livro));
    }

 

    @Override
    public List<LivroReponseDTO> listarTodos() {
       return livroRepository.findAll()
            .stream()
            .map(this::paraDTO)
            .collect(Collectors.toList()); 
    }

    @Override
    public LivroReponseDTO buscarId(Long id) {
        Livro livro = livroRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado com ID")); 
        
        return paraDTO(livro); 
        
    }

    @Override
    public LivroReponseDTO atualizar(Long id, LivroPostPutDto dto) {
        Livro livroExistente = livroRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Livro não encontrado com ID")); 
        Autor autor = autorRepository.findById(dto.getIdAutor())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID"));
        livroExistente.setIsbn(dto.getIsbn());
        livroExistente.setTitulo(dto.getTitulo());
        livroExistente.setAno(dto.getAno());
        livroExistente.setEdicao(dto.getEdicao());
        livroExistente.setAutor(autor);
        
        return paraDTO(livroRepository.save(livroExistente)); 
    }

    @Override
    public void deletar(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado com ID: " + id); 
            
        } 
        livroRepository.deleteById(id);
    }

    @Override
    public List<LivroReponseDTO> buscarPortitulo(String titulo) {
        Livro filtroTitulo = Livro.builder().titulo(titulo).build(); 
        ExampleMatcher matcher = ExampleMatcher.matching()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        .withIgnoreCase();
        
        return livroRepository.findAll(Example.of(filtroTitulo, matcher))
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList()); 
    }

    @Override
    public List<LivroReponseDTO> buscarPorIsbn(String isbn) {
        Livro filtroIsbn = Livro.builder().isbn(isbn).build(); 
        ExampleMatcher matcher = ExampleMatcher.matching()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        .withIgnoreCase(); 

        return livroRepository.findAll(Example.of(filtroIsbn, matcher))
        .stream()
        .map(this :: paraDTO)
        .collect(Collectors.toList());

    }


    private LivroReponseDTO paraDTO(Livro livro) {
     return  LivroReponseDTO.builder()
        .id(livro.getId())
        .isbn(livro.getIsbn())
        .titulo(livro.getTitulo())
        .ano(livro.getAno())
        .edicao(livro.getEdicao())
        .idAutor(livro.getAutor() != null ? livro.getAutor().getId() : null)
        .nomeAutor(livro.getAutor() != null ? livro.getAutor().getNomeAutor() : null)
        .build();
    }
    
}
