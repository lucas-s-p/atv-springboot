package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Resposta de Livro com o autor aninhado, evitando expor a entidade JPA diretamente.
// Author: lukasnascimento1
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("autor")
    private AutorResponseDto autor;

    @JsonProperty("ano")
    private Integer ano;

    @JsonProperty("edicao")
    private Integer edicao;
}
