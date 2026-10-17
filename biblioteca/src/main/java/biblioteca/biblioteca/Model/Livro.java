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
    
    // (ISBN, Título, Autor, Ano e Edição)

    @Id
    @Column(name = "ds_isbn", nullable = false)
    @JsonProperty("isbn")
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ds_autor", nullable = false)
    private Autor autor;

    @Column(name = "ds_titulo", nullable = false)
    @JsonProperty("titulo")
    private String titulo;

    @Column(name = "ds_ano", nullable = false)
    @JsonProperty("ano")
    private int ano;

    @Column(name = "ds_edicao", nullable = false)
    @JsonProperty("edicao")
    private int edicao;
}
