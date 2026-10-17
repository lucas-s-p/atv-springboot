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
    // (ISBN, Título, Autor, Ano e Edição)

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("autorId")
    private Long autorId;

    @JsonProperty("ano")
    private int ano;

    @JsonProperty("edicao")
    private int edicao;
}