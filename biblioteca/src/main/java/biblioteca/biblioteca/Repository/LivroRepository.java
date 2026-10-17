package biblioteca.biblioteca.Repository;

import biblioteca.biblioteca.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Acesso a dados de Livro, com buscas parciais (case-insensitive) por ISBN e título.
// Author: lukasnascimento1
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByIsbnContainingIgnoreCase(String isbn);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    boolean existsByIsbn(String isbn);
}
