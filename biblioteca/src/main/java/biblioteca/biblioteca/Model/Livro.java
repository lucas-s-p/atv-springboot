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
    private Long id;

    @Column(name = "ds_isbn", nullable = false)
    private String isbn;

    @Column(name = "ds_titulo", nullable = false)
    private String titulo;

    @Column(name = "ds_ano", nullable = false)
    private String ano;

    @Column(name = "ds_edicao", nullable = false)
    private String edicao;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;
}