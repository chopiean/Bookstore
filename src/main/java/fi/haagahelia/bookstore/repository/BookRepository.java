package fi.haagahelia.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fi.haagahelia.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}