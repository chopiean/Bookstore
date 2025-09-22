package fi.haagahelia.bookstore.repository;

import fi.haagahelia.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;;

@RepositoryRestResource(path = "books")
public interface BookJpaRepository extends JpaRepository<Book, Long> {

}
