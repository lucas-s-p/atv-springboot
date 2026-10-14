package biblioteca.biblioteca.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//  Livro (ISBN, Título, Autor, Ano e Edição),
@Entity
@Table(name = "tb_livro")
@Data
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("isbn")
    private Long ISBN;

    @Column(name = "titulo")
    @JsonProperty("titulo")
    private String titulo;

    @Column(name = "autor")
    private Autor autor;

}
