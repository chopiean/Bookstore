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

public class BookRepositoryTest {

    @Autowired
    private BookJpaRepository bookRepo;

    @Autowired
    private CategoryJpaRepository categoryRepo;

    @Test
    public void createNewBook() {
        Category category = categoryRepo.save(new Category("Adventure"));
        Book book = new Book("The Lost World", "Arthur Conan Doyle", 1912, "978000000", 25.0, category);
        bookRepo.save(book);

        List<Book> books = bookRepo.findByTitle("The Lost World");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Arthur Conan Doyle");
    }

    @Test
    public void deleteBook() {
        Category category = categoryRepo.save(new Category("Fantasy"));
        Book book = new Book("Delete Me", "Anonymous", 2023, "12345", 10.0, category);
        bookRepo.save(book);

        List<Book> books = bookRepo.findByTitle("Delete Me");
        assertThat(books).isNotEmpty();

        bookRepo.delete(books.get(0));

        List<Book> afterDelete = bookRepo.findByTitle("Delete Me");
        assertThat(afterDelete).isEmpty();
    }

    @Test
    public void searchBookByTitle() {
        Category category = categoryRepo.save(new Category("Sci-Fi"));
        Book book = new Book("Starship", "Isaac Asimov", 1950, "11111", 15.0, category);
        bookRepo.save(book);

        List<Book> result = bookRepo.findByTitle("Starship");
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getTitle()).isEqualTo("Starship");
    }
}
