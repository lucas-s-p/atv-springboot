package biblioteca.biblioteca.Repository;

import biblioteca.biblioteca.Model.Livro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
  List<Livro> findByTituloLivroContainingIgnoreCaseOrIsbnLivroContainingIgnoreCase(String titulo, String isbn);
}