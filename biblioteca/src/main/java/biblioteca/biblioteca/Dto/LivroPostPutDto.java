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
    @JsonProperty("titulo")
    @NotBlank(message = "Titulo do livro não pode ser vazio")
    private String titulo;

    @JsonProperty("isbn")
    @NotBlank(message = "ISBN do livro não pode ser vazio")
    private String isbn;

    @JsonProperty("autor_id")
    private Long autorId;

    @JsonProperty("ano")
    @NotBlank(message = "Ano do livro não pode ser vazia")
    private String ano;

    @JsonProperty("edicao")
    private int edicao;

}
