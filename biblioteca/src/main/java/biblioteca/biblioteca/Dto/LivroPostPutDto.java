package biblioteca.biblioteca.Dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @JsonProperty("tituloLivro")
    @NotBlank(message = "Título do livro não pode ser vazio")
    private String tituloLivro;

    @JsonProperty("autorID")
    @NotNull(message = "Autor do livro não pode ser vazio")
    private Long autorId;

    @JsonProperty("ano")
    @NotNull(message = "Ano do livro não pode ser vazio")
    private LocalDate ano;

    @JsonProperty("edicao")
    @NotBlank(message = "Edição do livro não pode ser vazia")
    private String edicao;
}
