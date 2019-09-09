package com.tzvika.library;

import com.tzvika.library.models.Book;
import com.tzvika.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/")
    public void createBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable("id") String id) {
        return bookRepository.findById(id);
    }
    @PutMapping("/{id}")
    public void updateBook(@PathVariable("id") String id, @RequestBody Book book) {
        book.setId(id);
        bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") String id) {
        bookRepository.deleteById(id);
    }
}
