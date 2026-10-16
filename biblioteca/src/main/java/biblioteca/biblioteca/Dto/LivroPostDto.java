package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroPostDto {
    @JsonProperty("isbn")
    @NotBlank(message = "ISBN do livro não pode ser vazio")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "Título do livro não pode ser vazio")
    private String titulo;

    @JsonProperty("ano")
    @NotBlank(message = "O ano do livro não pode ser vazio")
    @Pattern(regexp = "\\d{4}", message = "O ano deve ter 4 dígitos.")
    private String ano;

    @JsonProperty("edicao")
    @NotBlank(message = "A edição do livro não pode ser vazio")
    private String edicao; 
    
    @JsonProperty("idAutor")
    @NotNull(message = "Id do autor não deve ser vazio")
    private Long idAutor;
}