package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroPostPutDto {
    @JsonProperty("nomeLivro")
    @NotBlank(message = "Nome do livro não pode ser vazio")
    private String nomeLivro;

    @JsonProperty("ano")
    @Positive(message = "O ano deve ser um número válido")
    private Integer ano;

    @JsonProperty("edicao")
    private String edicao;

    @JsonProperty("autorId")
    private Long autorId;
}
