package biblioteca.biblioteca.Controller;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
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
            @RequestBody @Valid LivroPostPutDto livroPostPutDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(livroCrudService.criarLivro(livroPostPutDto));
    }

    @GetMapping("")
    public ResponseEntity<List<LivroResponseDto>> buscarTodosOsLivros() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroCrudService.buscarTodosOsLivros());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<LivroResponseDto>> buscarLivroPorTitulo(
        @RequestParam(required = true) String titulo
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroCrudService.buscarLivroPorTitulo(titulo));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<LivroResponseDto> buscarLivroPorIsbn(
        @RequestParam(required = true) String isbn
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroCrudService.buscarLivroPorIsbn(isbn));
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<LivroResponseDto> atualizarLivro(
            @PathVariable String isbn,
            @RequestBody @Valid LivroPostPutDto livroPostPutDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroCrudService.atualizarLivro(isbn, livroPostPutDto));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> removerLivro(@PathVariable String isbn) {
        livroCrudService.removerLivro(isbn);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}