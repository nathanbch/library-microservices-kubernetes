package com.tp.bookservice.loader;

import com.tp.bookservice.entity.Book;
import com.tp.bookservice.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadBooks(BookRepository repo) {
        return args -> {
            repo.save(new Book("1984", "George Orwell", "ISBN1"));
            repo.save(new Book("Dune", "Frank Herbert", "ISBN2"));
            repo.save(new Book("Le Petit Prince", "Antoine de Saint-Exupéry", "ISBN3"));
        };
    }
}
