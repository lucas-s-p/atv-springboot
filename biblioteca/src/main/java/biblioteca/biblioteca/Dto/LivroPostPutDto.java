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
    @NotNull(message = "ISBN não pode ser vazio")
    private Long isbn;
    
    @JsonProperty("titulo")
    @NotBlank(message = "Título não pode ser vazio")
    private String titulo;

    @JsonProperty("autorId")
    @NotNull(message = "Autor não pode ser vazio")
    private Long autorId;

    @Positive 
    @JsonProperty("ano")
    @NotNull(message = "Ano não pode ser vazio")
    private Integer ano;

    @Positive 
    @JsonProperty("edicao")
    @NotNull(message = "Edição não pode ser vazia")
    private Integer edicao;
}