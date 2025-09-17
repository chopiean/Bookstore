package fi.haagahelia.bookstore.repository;

import fi.haagahelia.bookstore.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    private final JdbcTemplate jdbc;

    public CategoryRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Category> mapper = (rs, rowNum) -> {
        Category c = new Category();
        c.setId(rs.getLong("id"));
        c.setName(rs.getString("name"));
        return c;
    };

    public List<Category> findAll() {
        return jdbc.query("SELECT id, name FROM category", mapper);
    }

    public Category findById(Long id) {
        String sql = "SELECT id, name FROM category WHERE id = ?";
        return jdbc.queryForObject(sql, mapper, id);
    }

    public Category findByName(String name) {
        String sql = "SELECT id, name FROM category WHERE name = ?";
        return jdbc.queryForObject(sql, mapper, name);
    }

    public void save(Category category) {
        jdbc.update("INSERT INTO category (name) VALUES (?)", category.getName());
    }

    public long count() {
        return jdbc.queryForObject("SELECT COUNT(*) FROM category", Long.class);
    }
}
