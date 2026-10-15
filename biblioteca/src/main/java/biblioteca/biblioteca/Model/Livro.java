package biblioteca.biblioteca.Model;

import jakarta.persistence.*;
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
    private Long id;

    @Column(name = "ds_isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "ds_titulo", nullable = false)
    private String titulo;

    @Column(name = "nr_ano", nullable = false)
    private Integer ano;

    @Column(name = "nr_edicao", nullable = false)
    private Integer edicao;

    // Cada livro pertence a exatamente um autor
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Autor autor;
}
