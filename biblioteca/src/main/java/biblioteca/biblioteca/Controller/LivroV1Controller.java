
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
@RequestMapping(
    value = "/v1/livros",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class LivroV1Controller {

    @Autowired
    LivroCrudService livroCrudService;

    @PostMapping("")
    public ResponseEntity<LivroResponseDto> criarLivro(
            @RequestBody @Valid LivroPostPutDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(livroCrudService.criarLivro(dto));
    }

    @GetMapping("")
    public ResponseEntity<List<LivroResponseDto>> buscarTodosLivros() {
        return ResponseEntity
                .ok(livroCrudService.buscarTodosLivros());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<LivroResponseDto> buscarLivro(
            @PathVariable String isbn) {
        return ResponseEntity
                .ok(livroCrudService.buscarLivro(isbn));
    }

    @GetMapping("/busca")
    public ResponseEntity<List<LivroResponseDto>> buscarPorTitulo(
            @RequestParam String titulo) {
        return ResponseEntity
                .ok(livroCrudService.buscarPorTitulo(titulo));
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<LivroResponseDto> atualizarLivro(
            @PathVariable String isbn,
            @RequestBody @Valid LivroPostPutDto dto) {
        return ResponseEntity
                .ok(livroCrudService.atualizarLivro(isbn, dto));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> removerLivro(
            @PathVariable String isbn) {
        livroCrudService.removerLivro(isbn);
        return ResponseEntity.noContent().build();
    }
}
