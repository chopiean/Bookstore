package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import fi.haagahelia.bookstore.model.Book;
import fi.haagahelia.bookstore.model.Category;
import fi.haagahelia.bookstore.model.User;
import fi.haagahelia.bookstore.repository.BookRepository;
import fi.haagahelia.bookstore.repository.CategoryRepository;
import fi.haagahelia.bookstore.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner demo(BookRepository bookRepo,
                                  CategoryRepository categoryRepo,
                                  UserRepository userRepo) {
        return (args) -> {
            // Categories
            if (categoryRepo.count() == 0) {
                categoryRepo.save(new Category("Programming"));
                categoryRepo.save(new Category("Databases"));
                categoryRepo.save(new Category("Fiction"));
            }

            // Books
            if (bookRepo.count() == 0) {
                Category prog = categoryRepo.findByName("Programming");
                Category db = categoryRepo.findByName("Databases");

                bookRepo.save(new Book("Clean Code", "Robert C. Martin", 2008,
                        "9780132350884", 39.99, prog));
                bookRepo.save(new Book("Effective Java", "Joshua Bloch", 2018,
                        "9780134685991", 45.00, prog));
                bookRepo.save(new Book("SQL Fundamentals", "John Patrick", 2005,
                        "9780137126026", 29.95, db));
            }

            // Users
            if (userRepo.count() == 0) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                userRepo.save(new User("user",
                        encoder.encode("user123"),
                        "user@email.com",
                        "USER"));

                userRepo.save(new User("admin",
                        encoder.encode("admin123"),
                        "admin@email.com",
                        "ADMIN"));
            }
        };
    }
}
