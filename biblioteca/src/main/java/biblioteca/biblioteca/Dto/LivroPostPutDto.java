package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroPostPutDto {
    @JsonProperty("isbn")
    @NotBlank(message = "ISBN não pode ser vazio")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "Título não pode ser vazio")
    private String titulo;

    @JsonProperty("edicao")
    @NotNull(message = "Edição não pode ser vazia")
    private Integer edicao;

    @JsonProperty("ano")
    @NotNull(message = "Ano não pode ser vazio")
    private Integer ano;

    @JsonProperty("id_autor")
    @NotNull(message = "Autor não pode ser vazio")
    private Long idAutor;
}
