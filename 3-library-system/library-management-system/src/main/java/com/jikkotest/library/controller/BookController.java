package com.jikkotest.library.controller;

import com.jikkotest.library.model.Book;
import com.jikkotest.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(books);
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        List<Book> availableBooks = bookService.getAvailableBooks();
        return availableBooks.isEmpty() ?
            ResponseEntity.noContent().build() :
            ResponseEntity.ok(availableBooks);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.ok(savedBook);
    }

    @PostMapping("/borrow/{isbn}")
    public ResponseEntity<String> borrowBook(@PathVariable String isbn) {
        boolean success = bookService.borrowBook(isbn);
        return success ?
            ResponseEntity.ok("Book borrowed") :
            ResponseEntity.badRequest().body("Book unavailable");
    }

    @PostMapping("/return/{isbn}")
    public ResponseEntity<String> returnBook(@PathVariable String isbn) {
        boolean success = bookService.returnBook(isbn);
        return success ?
            ResponseEntity.ok("Book returned") :
            ResponseEntity.badRequest().body("Book not borrowed");
    }
}
