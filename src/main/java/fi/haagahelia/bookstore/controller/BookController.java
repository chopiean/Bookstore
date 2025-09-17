package fi.haagahelia.bookstore.controller;

import fi.haagahelia.bookstore.model.Book;
import fi.haagahelia.bookstore.repository.BookRepository;
import fi.haagahelia.bookstore.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    
    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/booklist")
    public String showBookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll()); 
        return "addbook";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
    if (book.getCategory() != null && book.getCategory().getId() != null) {
        book.setCategory(categoryRepository.findById(book.getCategory().getId()).orElse(null));
    }
    bookRepository.save(book);
    return "redirect:/booklist";
}

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookRepository.findById(id).orElse(null));
        model.addAttribute("categories", categoryRepository.findAll()); // add dropdown data
        return "editbook";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book) {
    if (book.getCategory() != null && book.getCategory().getId() != null) {
        book.setCategory(categoryRepository.findById(book.getCategory().getId()).orElse(null));
    }
    bookRepository.save(book);
    return "redirect:/booklist";
}
}
