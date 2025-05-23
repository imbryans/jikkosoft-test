package com.jikkotest.library.service;

import com.jikkotest.library.model.Book;
import com.jikkotest.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findAll().stream()
                .filter(Book::isAvailable)
                .toList();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public boolean borrowBook(String isbn) {
        Optional<Book> bookOpt = bookRepository.findByIsbn(isbn);
        if (bookOpt.isPresent() && bookOpt.get().isAvailable()) {
            Book book = bookOpt.get();
            book.setAvailable(false);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    public boolean returnBook(String isbn) {
        Optional<Book> bookOpt = bookRepository.findByIsbn(isbn);
        if (bookOpt.isPresent() && !bookOpt.get().isAvailable()) {
            Book book = bookOpt.get();
            book.setAvailable(true);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    public boolean deleteBook(String isbn) {
        Optional<Book> bookOpt = bookRepository.findByIsbn(isbn);
        if (bookOpt.isPresent()) {
            bookRepository.delete(bookOpt.get());
            return true;
        }
        return false;
    }
}
