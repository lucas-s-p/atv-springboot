package biblioteca.biblioteca.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Service.LivroCrudService;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/v1/livros", produces = MediaType.APPLICATION_JSON_VALUE)
public class LivroV1Controller {
    
    @Autowired
    LivroCrudService livroCrudService;

    @PostMapping("")
    public ResponseEntity<LivroResponseDto> criarLivro(
            @RequestBody @Valid LivroPostPutDto livroPostPutDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(livroCrudService.criarLivro(livroPostPutDto));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> removerLivro(@PathVariable String isbn) {
        livroCrudService.removerLivro(isbn);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<LivroResponseDto> atualizarLivro(
            @PathVariable String isbn,
            @RequestBody @Valid LivroPostPutDto livroPostPutDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroCrudService.atualizarLivro(isbn, livroPostPutDto));
    }


    
    @GetMapping("/busca/{isbn}")
    public ResponseEntity<LivroResponseDto> buscarLivro(@PathVariable String isbn) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroCrudService.buscarLivro(isbn));
    }

   @GetMapping("") 
    public ResponseEntity<List<LivroResponseDto>> buscarTodosLivros() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroCrudService.buscarTodosLivros());
    }

    @GetMapping("/buscaLivro/{titulo}")
    public ResponseEntity<List<LivroResponseDto>> buscarLivroTitulo(String titulo) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroCrudService.buscarLivroTitulo(titulo));
    }

}