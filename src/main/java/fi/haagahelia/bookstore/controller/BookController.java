package fi.haagahelia.bookstore.controller;

import fi.haagahelia.bookstore.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @GetMapping("/index")
    public String index(Model model) {
        // Example book to pass to the view
        Book book = new Book("Effective Java", "Joshua Bloch", 2018, "9780134685991", 45.0);
        model.addAttribute("book", book);
        return "booklist";
    }
}
