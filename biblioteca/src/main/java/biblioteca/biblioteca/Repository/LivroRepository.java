package biblioteca.biblioteca.Repository;

import biblioteca.biblioteca.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    List<Livro> findByIsbnContainingIgnoreCase(String isbn);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    List<Livro> findByIsbnContainingIgnoreCaseOrTituloContainingIgnoreCase(String isbn, String titulo);

    // Remove todos os livros associados ao autor sem precisar mexer em Autor.java
    void deleteByAutorId(Long autorId);
}