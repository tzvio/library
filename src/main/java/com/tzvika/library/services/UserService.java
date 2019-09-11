package com.tzvika.library.services;

import com.tzvika.library.models.Book;
import com.tzvika.library.models.User;
import com.tzvika.library.repositories.BookRepository;
import com.tzvika.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        user.setBooks(getUserBooksByIds(user));
        userRepository.save(user);
    }

    public void updateUserById(User user, String id) {
        user.setId(id);
        user.setBooks(getUserBooksByIds(user));
        userRepository.save(user);
    }

    private List<Book> getUserBooksByIds(User user) {
        Iterator<String> userBookIdsIterator = user.getBookIds().iterator();
        List<Book> books = new ArrayList<Book>();
        while (userBookIdsIterator.hasNext()) {
            Book book =  bookRepository.findById(userBookIdsIterator.next()).get();
            if (book != null) {
                books.add(book);
            }
        }
        return books;
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }


    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

}
