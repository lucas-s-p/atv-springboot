package biblioteca.biblioteca.Repository;

import java.util.List;

import biblioteca.biblioteca.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, String> {
    public List<Livro> findByIsbnContaining(String isbn);

    public List<Livro> findByTituloContaining(String titulo);

    public List<Livro> findByIsbnContainingAndTituloContaining(String isbn, String titulo);
}
