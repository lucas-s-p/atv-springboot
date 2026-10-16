package biblioteca.biblioteca.Controller;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Service.LivroCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/livros")
public class LivroV1Controller {

    @Autowired
    private LivroCrudService livroService;

    @GetMapping
    public ResponseEntity<List<LivroResponseDto>> buscarTodos() {
        return ResponseEntity.ok(livroService.buscarTodosLivros());
    }

    @GetMapping("/isbn")
    public ResponseEntity<List<LivroResponseDto>> buscarPorIsbn(@RequestParam String isbn) {
        return ResponseEntity.ok(livroService.buscarPorIsbn(isbn));
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<LivroResponseDto>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(livroService.buscarPorTitulo(titulo));
    }

    @PostMapping
    public ResponseEntity<LivroResponseDto> criar(@RequestBody LivroPostPutDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.criarLivro(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDto> atualizar(@PathVariable Long id, @RequestBody LivroPostPutDto dto) {
        return ResponseEntity.ok(livroService.atualizarLivro(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        livroService.removerLivro(id);
        return ResponseEntity.noContent().build();
    }
}