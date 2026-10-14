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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("isbn")
    private Long isbn;

    @Column(name = "ds_nomeLivro", nullable = false)
    @JsonProperty("nomeLivro")
    private String nomeLivro;

    @Column(name = "ds_ano", nullable = true)
    @JsonProperty("ano")
    private Integer ano;

    @Column(name = "ds_edicao", nullable = true)
    @JsonProperty("edicao")
    private String edicao;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    
}
