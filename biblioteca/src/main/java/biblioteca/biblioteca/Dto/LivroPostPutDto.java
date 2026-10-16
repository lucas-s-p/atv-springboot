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

    @JsonProperty("tituloLivro")
    @NotBlank(message = "O livro precisa de um título")
    private String tituloLivro;

    @JsonProperty("isbn")
    @NotBlank(message = "O livro precisa de do código ISBN")
    private String isbn;

    @JsonProperty("ano")
    @NotBlank(message = "O ano é obrigatório")
    private String ano;

    @JsonProperty("edicao")
    @NotBlank(message = "Edição não pode ser vazia")
    private String edicao;

    @JsonProperty("autorId")
    @NotNull(message = "O livro precisa de um autor")
    private Long autorId;

}
