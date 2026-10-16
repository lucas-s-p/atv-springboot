package biblioteca.biblioteca.Model;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name = "tb_autor")
@Builder
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

    // Adicione a relação com Livro aqui
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "autor", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Livro> livros = new ArrayList<>();
}