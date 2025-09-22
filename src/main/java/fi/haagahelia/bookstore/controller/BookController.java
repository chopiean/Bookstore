package fi.haagahelia.bookstore.controller;

import fi.haagahelia.bookstore.model.Book;
import fi.haagahelia.bookstore.model.Category;
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

    // Show all books
    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    // Show add form
    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    // Save new book
    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        if (book.getCategory() != null && book.getCategory().getId() != null) {
            Category category = categoryRepository.findById(book.getCategory().getId());
            book.setCategory(category);
        }
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    // Delete book
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id);
        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("categories", categoryRepository.findAll());
            return "editbook";
        } else {
            return "redirect:/booklist";
        }
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book, @RequestParam Long categoryId) {
        Category category = categoryRepository.findById(categoryId);
        book.setCategory(category);
        bookRepository.update(book);
        return "redirect:/booklist";
    }
}

