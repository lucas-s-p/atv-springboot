package biblioteca.biblioteca.Dto;

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
    @NotBlank(message = "ISBN não pode ser vazio")
    @NotNull(message = "ISBN não pode ser nulo")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "Título não pode ser vazio")
    @NotNull(message = "Título não pode ser nulo")
    private String titulo;

    @JsonProperty("ano")
    @Positive(message = "Ano deve ser positivo")
    private int ano;

    @JsonProperty("autorId")
    @NotNull(message = "ID do autor não pode ser nulo")
    private Long autorId;
}
