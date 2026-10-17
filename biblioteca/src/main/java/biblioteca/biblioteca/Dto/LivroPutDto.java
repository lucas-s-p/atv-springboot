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
public class LivroPutDto {
    // ISBN é o identificador e não pode ser editado.

    @JsonProperty("titulo")
    @NotBlank(message = "O título do livro não pode ser vazia")
    private String titulo;

    @JsonProperty("autorId")
    @NotNull(message = "O autor do livro não pode ser vazio")
    private Long autorId;

    @JsonProperty("ano")
    @NotNull(message = "O ano do livro não pode ser vazio")
    private Integer ano;

    @JsonProperty("edicao")
    @NotNull(message = "A edição do livro não pode ser vazia")
    private Integer edicao;
}
