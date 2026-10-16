
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
    public ResponseEntity<List<LivroResponseDto>> buscarTodosLivros() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroCrudService.buscarTodosLivros());
    }

    @GetMapping("/{ISBN}")
    public ResponseEntity<LivroResponseDto> buscarLivro(@PathVariable Long ISBN) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroCrudService.buscarLivro(ISBN));
    }

    @PutMapping("/{ISBN}")
    public ResponseEntity<LivroResponseDto> atualizarLivro(
            @PathVariable Long ISBN,
            @RequestBody @Valid LivroPostPutDto livroPostPutDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(livroCrudService.atualizarLivro(ISBN, livroPostPutDto));
    }

    @DeleteMapping("/{ISBN}")
    public ResponseEntity<Void> removerLivro(@PathVariable Long ISBN) {
        livroCrudService.removerLivro(ISBN);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/buscar/titulo")
    public ResponseEntity<List<LivroResponseDto>> buscarPorTitulo(
        @RequestParam String titulo) {
        return ResponseEntity.ok(
            livroCrudService.buscarPorTitulo(titulo)
        );
    }
}
