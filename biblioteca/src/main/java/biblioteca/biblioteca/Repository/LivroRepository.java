package biblioteca.biblioteca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biblioteca.biblioteca.Model.Livro;

@Repository 
public interface LivroRepository extends  JpaRepository<Livro,Long>{
    
}
