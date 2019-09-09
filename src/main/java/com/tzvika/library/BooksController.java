package com.tzvika.library;

import com.tzvika.library.models.Book;
import com.tzvika.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
}
