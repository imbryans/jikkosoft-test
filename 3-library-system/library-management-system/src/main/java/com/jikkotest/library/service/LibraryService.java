package com.jikkotest.library.service;

import com.jikkotest.library.model.Book;
import com.jikkotest.library.model.Library;
import com.jikkotest.library.model.Member;
import com.jikkotest.library.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    public Library createLibrary(Library library) {
        return libraryRepository.save(library);
    }

    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    public Library getLibraryById(Long id) {
        return libraryRepository.findById(id).orElse(null);
    }

    public List<Book> getBooksByLibraryId(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElse(null);
        return library != null ? library.getBooks() : Collections.emptyList();
    }

    public List<Member> getMembersByLibraryId(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElse(null);
        return library != null ? library.getMembers() : Collections.emptyList();
    }
}
