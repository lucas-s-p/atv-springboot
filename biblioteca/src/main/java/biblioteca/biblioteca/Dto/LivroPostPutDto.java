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
public class LivroPostPutDto {
    @JsonProperty("ISBN")
    @NotBlank(message = "ISBN não pode ser vazio")
    private long isbn;
  
    @JsonProperty("Titulo")
    @NotBlank(message = "Titulo não pode ser vazio")
    private String titulo; 

    @JsonProperty("Autor")
    @NotBlank(message = "Nome do autor não pode ser vazio")
    private String autor;

    @JsonProperty("Ano")
    @NotBlank(message = "Ano não pode ser vazio")
    private int ano;

    @JsonProperty("Edicao")
    @NotBlank(message = "Edicao não pode ser vazia")
    private int edicao;
}
