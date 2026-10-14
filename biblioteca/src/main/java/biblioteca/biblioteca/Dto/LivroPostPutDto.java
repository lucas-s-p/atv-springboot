package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroPostPutDto {
    @JsonProperty("isbn")
    @NotBlank(message = "ISBN do livro não pode ser vazio")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "Título do livro não pode ser vazio")
    private String titulo;

    @JsonProperty("autorId")
    @NotNull(message = "O id do autor é obrigatório")
    private Long autorId;

    @JsonProperty("ano")
    @NotNull(message = "Ano do livro é obrigatório")
    private Integer ano;

    @JsonProperty("edicao")
    @NotNull(message = "Edição do livro é obrigatória")
    @Min(value = 1, message = "Edição deve ser maior ou igual a 1")
    private Integer edicao;
}
