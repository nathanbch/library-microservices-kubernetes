package com.tp.userservice.service;

import com.tp.userservice.client.BookClient;
import com.tp.userservice.entity.Borrow;
import com.tp.userservice.repository.BorrowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookClient bookClient;

    public BorrowService(BorrowRepository borrowRepository, BookClient bookClient) {
        this.borrowRepository = borrowRepository;
        this.bookClient = bookClient;
    }

    public Borrow borrowBook(Long userId, Long bookId, int days) {
        bookClient.reserveBook(bookId);

        Borrow borrow = new Borrow(
                userId,
                bookId,
                LocalDate.now(),
                LocalDate.now().plusDays(days)
        );

        return borrowRepository.save(borrow);
    }

    public void returnBook(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId).orElseThrow();
        bookClient.returnBook(borrow.getBookId());
        borrowRepository.delete(borrow);
    }
}
