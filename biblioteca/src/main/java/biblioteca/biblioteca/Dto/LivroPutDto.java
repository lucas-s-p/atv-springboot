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
public class LivroPutDto {
    @JsonProperty("tituloLivro")
    @NotBlank(message = "Título não pode ser vazio")
    private String tituloLivro;

    @JsonProperty("autorId")
    @NotNull(message = "Id do autor não pode ser vazio")
    private Long autorId;

    @JsonProperty("ano")
    @Positive(message = "Ano deve ser positivo")
    private int ano;

    @JsonProperty("edicao")
    @Positive(message = "Edição deve ser positiva")
    private int edicao;
}