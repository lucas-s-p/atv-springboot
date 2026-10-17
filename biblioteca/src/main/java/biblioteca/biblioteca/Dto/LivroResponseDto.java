package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import biblioteca.biblioteca.Model.Autor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDto {
    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("autor")
    private Autor autor;

    @JsonProperty("ano")
    private String ano;

    @JsonProperty("edicao")
    private String edicao;
}