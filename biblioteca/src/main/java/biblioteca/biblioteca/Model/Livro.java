package biblioteca.biblioteca.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Table(name = "tb_livro")
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    /// Livro (ISBN, Título, Autor, Ano e Edição)
    
    @Id
    @Column(name = "ISBN", nullable = false)
    @JsonProperty("ISBN")
    private long isbn;

    @Column(name = "titulo", nullable = false)
    @JsonProperty("Titulo")
    private String titulo; 

    @Column(name = "autor", nullable = false)
    @JsonProperty("Autor")
    private String autor;

    @Column(name = "ano", nullable = false)
    @JsonProperty("Ano")
    private int ano;

    @Column(name = "edicao", nullable = false)
    @JsonProperty("Edicao")
    private int edicao;

}
