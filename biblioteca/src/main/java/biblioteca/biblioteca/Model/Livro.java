package biblioteca.biblioteca.Model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "nu_ano", nullable = false)
    private Integer ano;

    @Column(name = "nu_edicao", nullable = false)
    private Integer edicao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autor_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Autor autor;
}