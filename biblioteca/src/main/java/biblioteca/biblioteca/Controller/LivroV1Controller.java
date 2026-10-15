
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
    private LivroCrudService livroCrudService;

    @PostMapping
    public ResponseEntity<LivroResponseDto> criarLivro(
            @RequestBody @Valid LivroPostPutDto dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(livroCrudService.criarLivro(dto));
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDto>> buscarTodosLivros() {
        return ResponseEntity
                .ok(livroCrudService.buscarTodosLivros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDto> buscarLivro(
            @PathVariable Long id) {

        return ResponseEntity
                .ok(livroCrudService.buscarLivro(id));
    }

    @GetMapping("/buscar/isbn")
    public ResponseEntity<List<LivroResponseDto>> buscarPorIsbn(
            @RequestParam String isbn) {

        return ResponseEntity
                .ok(livroCrudService.buscarLivrosPorIsbn(isbn));
    }

    @GetMapping("/buscar/titulo")
    public ResponseEntity<List<LivroResponseDto>> buscarPorTitulo(
            @RequestParam String titulo) {

        return ResponseEntity
                .ok(livroCrudService.buscarLivrosPorTitulo(titulo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDto> atualizarLivro(
            @PathVariable Long id,
            @RequestBody @Valid LivroPostPutDto dto) {

        return ResponseEntity
                .ok(livroCrudService.atualizarLivro(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLivro(@PathVariable Long id) {
        livroCrudService.removerLivro(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
