package biblioteca.biblioteca.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import biblioteca.biblioteca.Model.Livro;

@Repository 
public interface LivroRepository extends JpaRepository<Livro, Long>{

    @Query("""
            SELECT l FROM Livro l
            WHERE (:isbn <> '' AND CAST(l.isbn AS string) LIKE CONCAT('%', :isbn, '%'))
                OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))
        """)
    List<Livro> buscarPorIsbnOuTitulo(@Param("isbn") String isbn, @Param("titulo") String titulo);
}