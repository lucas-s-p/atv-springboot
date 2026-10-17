package biblioteca.biblioteca.Repository;

import biblioteca.biblioteca.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, String> {
    public List<Livro> findByIsbnContainingIgnoreCase(String isbn);

    public List<Livro> findByTituloContainingIgnoreCase(String titulo);

    public List<Livro> findByIsbnContainingIgnoreCaseAndTituloContainingIgnoreCase(String isbn, String titulo);
}