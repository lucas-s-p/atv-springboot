package biblioteca.biblioteca.Model;

import java.time.LocalDate;

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

    @Column(name = "ds_isbn", nullable = false)
    @JsonProperty("isbn")
    private String isbn;

    @Column(name = "ds_tituloLivro", nullable = false)
    @JsonProperty("tituloLivro")
    private String tituloLivro;

    @ManyToOne(optional = false)
    @JoinColumn(name = "autor_id", nullable = false)
    @JsonProperty("autor")
    private Autor autor;

    @Column(name = "ds_ano", nullable = false)
    @JsonProperty("ano")
    private LocalDate ano;

    @Column(name = "ds_edicao", nullable = false)
    @JsonProperty("edicao")
    private String edicao;

}
