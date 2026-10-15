package biblioteca.biblioteca.Model;

import java.time.LocalTime;

import org.hibernate.annotations.Collate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Livro
 */
@Entity 
@Builder 
@Data 
@Table(name = "ds_livro")
@AllArgsConstructor 
@NoArgsConstructor 
public class Livro {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Id")
    private Long id; 

    @Column(name = "ds_isbn", nullable = false, unique = true)
    @JsonProperty("isbn")
    private String isbn;

    @Column(name = "ds_titulo" , nullable = false)
    @JsonProperty("titulo")
    private String titulo; 
    
    @Column(name = "ds_ano", nullable = false)
    @JsonProperty("ano")
    private LocalTime ano;
    
    @Column(name = "ds_edicao", nullable = false)
    private String edicao; 

    @ManyToOne 
    @JoinColumn( name = "autor" , nullable = false)
    private Autor autor; 


}
