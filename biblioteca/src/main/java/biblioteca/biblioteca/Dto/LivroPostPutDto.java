package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @NotBlank(message = "O livro deve ter ISBN")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "O livro deve ter título")
    private String titulo;

    @JsonProperty("autor_id")
    @NotNull(message = "O livro deve ter autor")
    private Long autor_id;

    @JsonProperty("ano")
    @NotBlank(message = "O livro deve ter ano")
    private String ano;

    @JsonProperty("edicao")
    @NotBlank(message = "O livro deve ter edição")
    private String edicao;
}