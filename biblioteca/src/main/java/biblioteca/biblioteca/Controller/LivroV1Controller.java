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
@RequestMapping(value = "/v1/Livros", produces = MediaType.APPLICATION_JSON_VALUE)
public class LivroV1Controller {

    @Autowired
    LivroCrudService LivroCrudService;

    @PostMapping("")
    public ResponseEntity<LivroResponseDto> criarLivro(
            @RequestBody @Valid LivroPostPutDto LivroPostPutDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(LivroCrudService.criarLivro(LivroPostPutDto));
    }

    @GetMapping("")
    public ResponseEntity<List<LivroResponseDto>> buscarTodosLivros() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(LivroCrudService.buscarTodosLivros());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<LivroResponseDto> buscarLivroPorISBN(@PathVariable String isbn) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(LivroCrudService.buscarLivroPorISBN(isbn));
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<LivroResponseDto>> buscarLivroPorTitulo(@RequestParam String titulo) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(LivroCrudService.buscarLivroPorTitulo(titulo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDto> atualizarLivro(
            @PathVariable Long id,
            @RequestBody @Valid LivroPostPutDto LivroPostPutDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(LivroCrudService.atualizarLivro(id, LivroPostPutDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLivro(@PathVariable Long id) {
        LivroCrudService.removerLivro(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
