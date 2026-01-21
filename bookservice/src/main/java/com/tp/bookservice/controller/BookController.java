package com.tp.bookservice.controller;

import com.tp.bookservice.entity.Book;
import com.tp.bookservice.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Book> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return repo.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        Book b = repo.findById(id).orElseThrow();
        b.setTitle(book.getTitle());
        b.setAuthor(book.getAuthor());
        b.setIsbn(book.getIsbn());
        return repo.save(b);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}/reserve")
    public Book reserve(@PathVariable Long id) {
        Book b = repo.findById(id).orElseThrow();
        if (!b.isAvailable()) throw new RuntimeException("Livre déjà emprunté");
        b.setAvailable(false);
        return repo.save(b);
    }

    @PutMapping("/{id}/return")
    public Book giveBack(@PathVariable Long id) {
        Book b = repo.findById(id).orElseThrow();
        b.setAvailable(true);
        return repo.save(b);
    }
}
