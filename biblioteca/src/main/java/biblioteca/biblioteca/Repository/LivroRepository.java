package biblioteca.biblioteca.Repository;

import biblioteca.biblioteca.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIsbn(String isbn);
    List<Livro> findByTitulo(String titulo);
}