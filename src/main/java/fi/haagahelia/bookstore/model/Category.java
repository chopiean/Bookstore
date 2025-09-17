package fi.haagahelia.bookstore.model;

public class Category {
    private Long id;   
    private String name;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
