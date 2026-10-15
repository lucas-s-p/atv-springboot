package biblioteca.biblioteca.Controller;

import biblioteca.biblioteca.Dto.AutorPostPutDto;
import biblioteca.biblioteca.Dto.AutorResponseDto;
import biblioteca.biblioteca.Service.AutorCrudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/autores", produces = MediaType.APPLICATION_JSON_VALUE)
public class AutorV1Controller {

    @Autowired
    AutorCrudService autorCrudService;

    @PostMapping("")
    public ResponseEntity<AutorResponseDto> criarAutor(
            @RequestBody @Valid AutorPostPutDto autorPostPutDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(autorCrudService.criarAutor(autorPostPutDto));
    }

    @GetMapping("")
    public ResponseEntity<List<AutorResponseDto>> buscarTodosAutores() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(autorCrudService.buscarTodosAutores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDto> buscarAutor(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(autorCrudService.buscarAutor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDto> atualizarAutor(
            @PathVariable Long id,
            @RequestBody @Valid AutorPostPutDto autorPostPutDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(autorCrudService.atualizarAutor(id, autorPostPutDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAutor(@PathVariable Long id) {
        autorCrudService.removerAutor(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
