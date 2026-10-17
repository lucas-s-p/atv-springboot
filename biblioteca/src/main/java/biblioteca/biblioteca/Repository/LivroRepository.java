package biblioteca.biblioteca.Repository;

import biblioteca.biblioteca.Model.Livro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByIsbn(String isbn);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}
