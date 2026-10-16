package biblioteca.biblioteca.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_livro") 
@Data 
@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
public class Livro {

    @Id
    @JsonProperty("isbn")
    private Long isbn;

    @Column(name = "lv_titulo", nullable = false)
    @JsonProperty("titulo") 
    private String titulo;

    @Column(name = "lv_ano")
    @JsonProperty("ano") 
    private Integer ano;

    @Column(name = "lv_edicao")
    @JsonProperty("edicao")
    private Integer edicao;

   
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autor", nullable = false)
    @JsonProperty("autor")
    private Autor autor;
}