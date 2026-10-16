package biblioteca.biblioteca.Model;

import java.time.Year;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//  Livro (ISBN, Título, Autor, Ano e Edição),
@Entity
@Table(name = "tb_livro")
@Data
@Builder 
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    @Id
    @Column(name = "isbn")
    @JsonProperty("isbn")
    private String isbn;

    @Column(name = "titulo")
    @JsonProperty("titulo")
    private String titulo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_autor")
    @JsonProperty("autor")
    private Autor autor;

    @Column(name = "ano")
    @JsonProperty("ano")
    private Integer ano;

    @Column(name = "edicao")
    @JsonProperty("edicao")
    private Integer edicao;
}