package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.model.Book;
import fi.haagahelia.bookstore.model.Category;
import fi.haagahelia.bookstore.repository.BookRepository;
import fi.haagahelia.bookstore.repository.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(BookRepository bookRepo, CategoryRepository categoryRepo) {
        return args -> {
            Category programming = categoryRepo.save(new Category("Programming"));
            Category design = categoryRepo.save(new Category("Design"));
            Category fiction = categoryRepo.save(new Category("Fiction"));

            bookRepo.save(new Book("Clean Code", "Robert C. Martin", 2008, "9780132350884", 39.90, programming));
            bookRepo.save(new Book("Effective Imagination", "Joshua Bloch", 2018, "9780134685991", 44.90, fiction));
            bookRepo.save(new Book("Refactoring", "Martin Fowler", 1999, "9780201485677", 42.00, design));
        };
    }
}
