package biblioteca.biblioteca.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "ds_tituloLivro", nullable = false)
    @JsonProperty("tituloLivro")
    private String tituloLivro;

    @Column(name = "ds_isbnLivro", nullable = false)
    @JsonProperty("isbnLivro")
    private String isbnLivro;

    @Column(name = "ds_anoPublicacao", nullable = false)
    @JsonProperty("anoPublicacao")
    private int anoPublicacao;

    @Column(name = "ds_edicaoLivro", nullable = false)
    @JsonProperty("edicaoLivro")
    private int edicaoLivro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    @JsonIgnoreProperties("livros")
    private Autor autor;
}
