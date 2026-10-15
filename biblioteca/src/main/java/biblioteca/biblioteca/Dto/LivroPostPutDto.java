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
    @NotBlank(message = "ISBN não pode ser vazio")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "Título não pode ser vazio")
    private String titulo;

    @JsonProperty("autorId")
    @NotNull(message = "Autor é obrigatório")
    private Long autorId;

    @JsonProperty("ano")
    @NotNull(message = "Ano é obrigatório")
    private Integer ano;

    @JsonProperty("edicao")
    @NotNull(message = "Edição é obrigatória")
    private Integer edicao;
}
