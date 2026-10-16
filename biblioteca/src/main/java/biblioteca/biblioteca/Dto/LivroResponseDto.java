package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("tituloLivro")
    private String tituloLivro;

    @JsonProperty("isbnLivro")
    private String isbnLivro;

    @JsonProperty("anoPublicacao")
    private int anoPublicacao;

    @JsonProperty("edicaoLivro")
    private int edicaoLivro;

    @JsonProperty("autor")
    private AutorResponseDto autor;
}