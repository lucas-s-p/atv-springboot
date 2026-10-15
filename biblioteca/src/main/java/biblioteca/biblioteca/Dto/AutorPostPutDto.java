package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutorPostPutDto {
    @JsonProperty("nomeAutor")
    @NotBlank(message = "Nome do autor não pode ser vazio")
    private String nomeAutor;

    @JsonProperty("nacionalidade")
    @NotBlank(message = "Nacionalidade do autor não pode ser vazia")
    private String nacionalidade;
}
