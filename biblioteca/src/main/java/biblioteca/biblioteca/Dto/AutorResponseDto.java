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
public class AutorResponseDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nomeAutor")
    private String nomeAutor;

    @JsonProperty("nacionalidade")
    private String nacionalidade;
}
