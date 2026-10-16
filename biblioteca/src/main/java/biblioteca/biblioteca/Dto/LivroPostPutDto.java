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
    @JsonProperty("isbnLivro")
    @NotBlank(message = "Número do ISBN não pode ser vazio")
    private String isbnLivro;

    @JsonProperty("tituloLivro")
    @NotBlank(message = "Título do Livro não pode ser vazio")
    private String tituloLivro;

    @JsonProperty("anoPublicacao")
    @NotNull(message = "Ano de publicação não pode ser vazio")
    private Integer anoPublicacao;

    @JsonProperty("edicaoLivro")
    @NotNull(message = "Número de edição não pode ser vazio")
    private Integer edicaoLivro;

    @JsonProperty("autor")
    @NotNull(message = "ID do autor não pode ser vazio")
    private Long autor;
}