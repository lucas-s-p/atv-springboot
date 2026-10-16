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
    @NotNull (message = "ISBN não pode ser nulo")
    private Long ISBN;

    @JsonProperty("titulo")
    @NotBlank(message = "Não pode ter titulo vazio")
    private String titulo;

    @JsonProperty("ano")
    @NotNull (message = "Ano não pode ser vazio")
    private Integer ano;

    @JsonProperty("autorId")
    @NotNull(message = "O autor é obrigatório")
    private Long autorId;

    @JsonProperty("edicao")
    @NotBlank(message = "Edição não pode ser vazia")
    private String edicao;

}
