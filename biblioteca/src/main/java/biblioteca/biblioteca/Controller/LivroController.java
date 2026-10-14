package biblioteca.biblioteca.Controller;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Service.LivroCrudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroCrudService livroCrudService;

    @PostMapping
    public ResponseEntity<LivroResponseDto> criarLivro(@Valid @RequestBody LivroPostPutDto livroPostPutDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCrudService.criarLivro(livroPostPutDto));
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDto>> buscarTodosLivros() {
        return ResponseEntity.ok(livroCrudService.buscarTodosLivros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDto> buscarLivro(@PathVariable Long id) {
        return ResponseEntity.ok(livroCrudService.buscarLivro(id));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<LivroResponseDto>> buscarLivrosPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(livroCrudService.buscarLivrosPorTitulo(titulo));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<List<LivroResponseDto>> buscarLivrosPorIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(livroCrudService.buscarLivrosPorIsbn(isbn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDto> atualizarLivro(@PathVariable Long id, @Valid @RequestBody LivroPostPutDto livroPostPutDto) {
        return ResponseEntity.ok(livroCrudService.atualizarLivro(id, livroPostPutDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLivro(@PathVariable Long id) {
        livroCrudService.removerLivro(id);
        return ResponseEntity.noContent().build();
    }
}