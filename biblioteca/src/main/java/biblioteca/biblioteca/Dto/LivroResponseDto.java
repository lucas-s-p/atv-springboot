package biblioteca.biblioteca.Dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("tituloLivro")
    private String tituloLivro;

    @JsonProperty("autor")
    private AutorResponseDto autor;

    @JsonProperty("ano")
    private LocalDate ano;

    @JsonProperty("edicao")
    private String edicao;
}
