package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDto {
    @JsonProperty("ISBN")
    private long isbn;
  
    @JsonProperty("Titulo")
    private String titulo; 

    @JsonProperty("Autor")
    private String autor;

    @JsonProperty("Ano")
    private int ano;

    @JsonProperty("Edicao")
    private int edicao;
    
}
