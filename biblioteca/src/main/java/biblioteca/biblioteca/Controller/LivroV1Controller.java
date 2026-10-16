package biblioteca.biblioteca.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Service.LivroCrudService;
import org.springframework.http.HttpStatus;

import java.util.List;


@RestController
@RequestMapping(value = "/v1/livros", produces = MediaType.APPLICATION_JSON_VALUE)
public class LivroV1Controller {

    @Autowired
    LivroCrudService livroCrudService;

    @PostMapping("")
    public ResponseEntity<LivroResponseDto> criarLivro(@RequestBody @Valid LivroPostPutDto livroPostPutDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCrudService.criarLivro(livroPostPutDto));
    }

    @GetMapping("")
    public ResponseEntity<List<LivroResponseDto>> buscarTodosOsLivros() {
        return ResponseEntity.status(HttpStatus.OK).body(livroCrudService.buscarTodosLivros());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<LivroResponseDto>> buscarLivro(@RequestParam(required = false) String titulo, @RequestParam(required = false) String isbn) {
            return ResponseEntity.status(HttpStatus.OK).body(livroCrudService.buscarLivro(titulo, isbn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDto> atualizarLivro(@PathVariable Long id, @RequestBody @Valid LivroPostPutDto livroPostPutDto) {
        return ResponseEntity.status(HttpStatus.OK).body(livroCrudService.atualizarLivro(id, livroPostPutDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLivro(@PathVariable Long id) {
        livroCrudService.removerLivro(id);
        return ResponseEntity.noContent().build();
    }
}
