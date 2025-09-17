package fi.haagahelia.bookstore.repository;

import fi.haagahelia.bookstore.model.Book;
import fi.haagahelia.bookstore.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbc;

    public BookRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Book> mapper = (rs, rowNum) -> {
        Book b = new Book();
        b.setId(rs.getLong("id"));
        if (rs.wasNull()) {
            b.setId(null); 
        }
        b.setTitle(rs.getString("title"));
        b.setAuthor(rs.getString("author"));
        b.setIsbn(rs.getString("isbn"));
        b.setPrice(rs.getDouble("price"));
        b.setPublicationYear(rs.getInt("publication_year"));

        Long categoryId = rs.getLong("category_id");
        if (!rs.wasNull()) {
            Category c = new Category();
            c.setId(categoryId);
            c.setName(rs.getString("category_name"));
            b.setCategory(c);
        }

        return b;
    };

    public List<Book> findAll() {
        String sql = """
            SELECT b.id, b.title, b.author, b.isbn, b.price, b.publication_year,
                   c.id AS category_id, c.name AS category_name
            FROM book b
            LEFT JOIN category c ON b.category_id = c.id
            """;
        return jdbc.query(sql, mapper);
    }

    public Book findById(Long id) {
        String sql = """
            SELECT b.id, b.title, b.author, b.isbn, b.price, b.publication_year,
                   c.id AS category_id, c.name AS category_name
            FROM book b
            LEFT JOIN category c ON b.category_id = c.id
            WHERE b.id = ?
            """;
        return jdbc.queryForObject(sql, mapper, id);
    }

    public void save(Book book) {
        Long categoryId = (book.getCategory() != null ? book.getCategory().getId() : null);

        jdbc.update(
            "INSERT INTO book (title, author, isbn, price, publication_year, category_id) VALUES (?, ?, ?, ?, ?, ?)",
            book.getTitle(),
            book.getAuthor(),
            book.getIsbn(),
            book.getPrice(),
            book.getPublicationYear(),
            categoryId 
        );
    }

    public void update(Book book) {
        Long categoryId = (book.getCategory() != null ? book.getCategory().getId() : null);
        Long bookId = book.getId(); 

        jdbc.update(
            "UPDATE book SET title=?, author=?, isbn=?, price=?, publication_year=?, category_id=? WHERE id=?",
            book.getTitle(),
            book.getAuthor(),
            book.getIsbn(),
            book.getPrice(),
            book.getPublicationYear(),
            categoryId,
            bookId 
        );
    }

    public void deleteById(Long id) {
        jdbc.update("DELETE FROM book WHERE id=?", id);
    }

    public long count() {
        return jdbc.queryForObject("SELECT COUNT(*) FROM book", Long.class);
    }
}
