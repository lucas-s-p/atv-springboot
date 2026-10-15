package biblioteca.biblioteca.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Column(name = "ds_titulo", nullable = false)
    @JsonProperty("titulo")
    private String titulo;

    @Column(name = "nr_ano", nullable = false)
    @JsonProperty("ano")
    private Integer ano;

    @Column(name = "nr_edicao", nullable = false)
    @JsonProperty("edicao")
    private Integer edicao;

    @ManyToOne
    @JoinColumn(name = "fk_autor", nullable = false)
    @JsonProperty("autor")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Autor autor;
}
