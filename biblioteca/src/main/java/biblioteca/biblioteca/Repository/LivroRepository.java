package biblioteca.biblioteca.Repository;

import biblioteca.biblioteca.Model.Livro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, String> {

    @Query("SELECT l FROM Livro l WHERE l.titulo LIKE %:titulo%")
    List<Livro> buscarPorTitulo(@Param("titulo") String titulo);
}