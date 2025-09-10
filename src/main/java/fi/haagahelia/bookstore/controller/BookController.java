package fi.haagahelia.bookstore.controller;

import fi.haagahelia.bookstore.model.Book;
import fi.haagahelia.bookstore.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
public class BookController {

    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/booklist")
    public String showBookList(Model model) {
        model.addAttribute("books", repo.findAll());
        return "booklist"; 
    }

    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
       repo.save(book);
       return "redirect:/booklist";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        repo.deleteById(id);
        return "redirect:/booklist";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
       Book book = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid book id:" + id));
            model.addAttribute("book", book);
            return "editbook";
    }
    
    @PostMapping("/update")
    public String update(@ModelAttribute("book") Book book) {
        repo.save(book);
        return "redirect:booklist";
    }

    
    
    
}
