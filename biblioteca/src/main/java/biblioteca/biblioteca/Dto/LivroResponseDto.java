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
    @JsonProperty("isbn")
    private Long isbn;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("autorId")
    private Long autorId;

    @JsonProperty("ano")
    private Integer ano;

    @JsonProperty("edicao")
    private String edicao;
}
