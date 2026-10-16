package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroPostPutDto {

    @JsonProperty("isbn")
    @NotBlank(message = "ISBN é obrigatório")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @JsonProperty("ano")
    @NotNull(message = "Ano é obrigatório")
    @Positive(message = "Ano deve ser positivo")
    private Integer ano;

    @JsonProperty("edicao")
    @NotNull(message = "Edição é obrigatória")
    @Positive(message = "Edição deve ser positiva")
    private Integer edicao;

    @JsonProperty("autorId")
    @NotNull(message = "autorId é obrigatório")
    private Long autorId;
}