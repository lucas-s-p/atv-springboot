
package biblioteca.biblioteca.Repository;

import biblioteca.biblioteca.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Busca livros cujo ISBN contenha o texto informado.
    List<Livro> findByIsbnContainingIgnoreCase(String isbn);

    // Busca livros cujo título contenha o texto informado.
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}
