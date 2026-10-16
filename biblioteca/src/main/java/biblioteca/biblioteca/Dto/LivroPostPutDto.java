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
    @NotBlank(message = "O ISBN não pode ser vazio")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "O Título não pode ser vazio")
    private String titulo;

    @JsonProperty("ano")
    @NotNull(message = "O Ano não pode ser nulo")
    private Integer ano;

    @JsonProperty("edicao")
    @NotBlank(message = "A Edição não pode ser vazia")
    private String edicao;

    @JsonProperty("autorId")
    @NotNull(message = "O ID do autor não pode ser nulo")
    private Long autorId;
}