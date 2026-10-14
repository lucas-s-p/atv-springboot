package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ISBN, Título, Autor, Ano e Edição!!!!
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroPostPutDto {
    @JsonProperty("isbn")
    @NotBlank(message = "ISBN não pode ser vazio")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "Título não pode ser vazio")
    private String titulo;

    @JsonProperty("autor")
    @NotBlank(message = "Nome do autor não pode ser vazio")
    private String autor;

    @JsonProperty("ano")
    @NotBlank(message = "Ano não pode ser vazio")
    private String ano;

    @JsonProperty("edicao")
    @NotBlank(message = "Edição não pode ser vazia ")
    private String edicao;
}