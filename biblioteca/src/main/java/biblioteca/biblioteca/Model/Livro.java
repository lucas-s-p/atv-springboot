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
    @JsonProperty("id")
    private Long id;

    @Column(name = "ds_ISBN", nullable = false, unique = true)
    @JsonProperty("ISBN")
    private String isbn;

    @Column(name = "ds_titulo", nullable = false)
    @JsonProperty("titulo")
    private String titulo;
    
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    @JsonProperty("autor")
    private Autor autor;

    @Column(name = "ds_ano", nullable = false)
    @JsonProperty("ano")
    private int ano;
    
    @Column(name = "ds_edicao", nullable = false)
    @JsonProperty("edicao")
    private String edicao;
    
}
