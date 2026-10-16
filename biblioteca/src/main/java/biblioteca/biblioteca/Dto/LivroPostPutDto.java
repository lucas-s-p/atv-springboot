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
    @NotBlank(message = "O ISBN do livro não pode ser vazio")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "Título do livro não pode ser vazio")
    private String titulo;

    @JsonProperty("id_autor")
    @NotNull(message = "iD do autor não pode ser vazio")
    private Long idAutor;

    @JsonProperty("ano")
    @NotNull(message = "Ano de publicação não pode ser vazio")
    private Integer ano;

    @JsonProperty("edicao")
    @NotNull(message = "Edição não pode ser vazia")
    private Integer edicao;
}