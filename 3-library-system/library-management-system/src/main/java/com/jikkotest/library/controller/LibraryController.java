package com.jikkotest.library.controller;

import com.jikkotest.library.model.Book;
import com.jikkotest.library.model.Library;
import com.jikkotest.library.model.Member;
import com.jikkotest.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @PostMapping("/createLibrary")
    public ResponseEntity<Library> createLibrary(@RequestBody Library library) {
        Library createdLibrary = libraryService.createLibrary(library);
        return ResponseEntity.ok(createdLibrary);
    }

    @GetMapping("/getAllLibraries")
    public ResponseEntity<List<Library>> getAllLibraries() {
        List<Library> libraries = libraryService.getAllLibraries();
        return libraries.isEmpty() ?
            ResponseEntity.noContent().build() :
            ResponseEntity.ok(libraries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> getLibraryById(@PathVariable Long id) {
        Library library = libraryService.getLibraryById(id);
        return library != null ?
            ResponseEntity.ok(library) :
            ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/getBooks")
    public ResponseEntity<List<Book>> getBooksByLibrary(@PathVariable Long id) {
        List<Book> books = libraryService.getBooksByLibraryId(id);
        return books.isEmpty() ?
            ResponseEntity.noContent().build() :
            ResponseEntity.ok(books);
    }

    @GetMapping("/{id}/getMembers")
    public ResponseEntity<List<Member>> getMembersByLibrary(@PathVariable Long id) {
        List<Member> members = libraryService.getMembersByLibraryId(id);
        return members.isEmpty() ?
            ResponseEntity.noContent().build() :
            ResponseEntity.ok(members);
    }

}
