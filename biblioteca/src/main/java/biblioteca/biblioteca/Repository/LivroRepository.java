package biblioteca.biblioteca.Repository;

import biblioteca.biblioteca.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    boolean existsByIsbn(String isbn);

    List<Livro> findByIsbnContainingIgnoreCase(String isbn);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    List<Livro> findByIsbnContainingIgnoreCaseAndTituloContainingIgnoreCase(String isbn, String titulo);
}
