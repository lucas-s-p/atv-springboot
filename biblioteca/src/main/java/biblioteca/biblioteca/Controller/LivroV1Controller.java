package biblioteca.biblioteca.Controller;

import biblioteca.biblioteca.Dto.LivroPostDto;
import biblioteca.biblioteca.Dto.LivroPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Service.LivroCrudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/livros", produces = MediaType.APPLICATION_JSON_VALUE)
public class LivroV1Controller {

    @Autowired
    LivroCrudService livroCrudService;

    @PostMapping("")
    public ResponseEntity<LivroResponseDto> criarLivro(
            @RequestBody @Valid LivroPostDto livroPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(livroCrudService.criarLivro(livroPostDto));
    }

    @GetMapping("")
    public ResponseEntity<List<LivroResponseDto>> buscarTodosLivros(
        @RequestParam(required = false) String isbn, @RequestParam(required = false) String titulo
    ) {

        boolean isIsbn = isbn != null && !isbn.isBlank();
        boolean isTitulo = titulo != null && !titulo.isBlank();

        if (isIsbn && isTitulo) {
            return ResponseEntity.status(HttpStatus.OK).body(livroCrudService.buscarTodosLivrosIsbnTitulo(isbn, titulo));
        } else if (isIsbn) {
            return ResponseEntity.status(HttpStatus.OK).body(livroCrudService.buscarLivrosIsbn(isbn));
        } else if (isTitulo) {
            return ResponseEntity.status(HttpStatus.OK).body(livroCrudService.buscarLivrosTitulo(titulo));
        }

        return ResponseEntity.status(HttpStatus.OK).body(livroCrudService.buscarTodosLivros());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<LivroResponseDto> buscarLivro(@PathVariable String isbn) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroCrudService.buscarLivro(isbn));
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<LivroResponseDto> atualizarLivro(
            @PathVariable String isbn,
            @RequestBody @Valid LivroPutDto livroPutDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroCrudService.atualizarLivro(isbn, livroPutDto));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> removerLivro(@PathVariable String isbn) {
        livroCrudService.removerLivro(isbn);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}