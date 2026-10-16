package biblioteca.biblioteca.Repository;

import biblioteca.biblioteca.Model.Livro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT l FROM Livro l WHERE CAST(l.isbn AS string) LIKE CONCAT('%', :isbn, '%')")
    List<Livro> buscarPorISBNParcial(@Param("isbn") String isbn);
    
    @Query("SELECT l FROM Livro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Livro> buscarPorTitulo(@Param("titulo") String titulo);
}
