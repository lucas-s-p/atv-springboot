package biblioteca.biblioteca.Dto;

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

    @JsonProperty("ISBN")
    private String isbn;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("autor")
    private AutorResponseDto autor;

    @JsonProperty("ano")
    private int ano;

    @JsonProperty("edicao")
    private String edicao;
}
