package fi.haagahelia.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @JsonIgnoreProperties("category")  // prevent infinite loop (Book → Category → Book…)
    private List<Book> books;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Book> getBooks() { return books; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBooks(List<Book> books) { this.books = books; }
}
