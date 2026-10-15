package biblioteca.biblioteca.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@Table(name = "tb_autor")
@AllArgsConstructor
@NoArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "ds_nomeAutor", nullable = false)
    @JsonProperty("nomeAutor")
    private String nomeAutor;

    @Column(name = "ds_nacionalidade", nullable = false)
    @JsonProperty("nacionalidade")
    private String nacionalidade;

    @JsonProperty("livros")
    @OneToMany(mappedBy = "autor", cascade = CascadeType.REMOVE)
    private List<Livro> livros;
}