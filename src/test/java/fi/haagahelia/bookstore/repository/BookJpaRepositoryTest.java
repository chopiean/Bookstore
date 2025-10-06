package fi.haagahelia.bookstore.repository;

import fi.haagahelia.bookstore.model.Book;
import fi.haagahelia.bookstore.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")

public class BookJpaRepositoryTest {

    @Autowired
    private BookJpaRepository bookRepo;

    @Autowired
    private CategoryJpaRepository categoryRepo;

    @Test
    public void testCreateBook() {
        Category cat = categoryRepo.save(new Category("Test Category"));
        Book book = new Book("Clean Code", "Robert Martin", 2008, "9780132350884", 39.99, cat);
        bookRepo.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void testFindBooks() {
        Category cat = categoryRepo.save(new Category("Programming"));
        bookRepo.save(new Book("Effective Java", "Joshua Bloch", 2018, "9780134685991", 45.00, cat));
        List<Book> books = bookRepo.findAll();
        assertThat(books).isNotEmpty();
    }

    @Test
    public void testDeleteBook() {
        Category cat = categoryRepo.save(new Category("Fiction"));
        Book book = bookRepo.save(new Book("Test Delete", "Author", 2021, "0000000000000", 19.99, cat));
        bookRepo.delete(book);
        assertThat(bookRepo.findById(book.getId())).isEmpty();
    }
}
