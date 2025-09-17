package booklistjdbc;

public class Book {
    int id;
    String title;
    String author;
    int year;
    String isbn;
    double price;

    public Book(int id, String title, String author, int year, String isbn, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.price = price;
    }

    public Book(String title, String author, int year, String isbn, double price) {
        this(0, title, author, year, isbn, price);
    }

    @Override
    public String toString() {
        return id + ": " + title + " by " + author + " (" + year + "), ISBN=" + isbn + ", $" + price;
    }
}
