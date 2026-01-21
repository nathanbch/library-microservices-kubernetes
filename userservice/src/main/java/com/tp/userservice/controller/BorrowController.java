package com.tp.userservice.controller;

import com.tp.userservice.entity.Borrow;
import com.tp.userservice.service.BorrowService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public Borrow borrow(@RequestParam Long userId,
                         @RequestParam Long bookId,
                         @RequestParam int days) {
        return borrowService.borrowBook(userId, bookId, days);
    }

    @DeleteMapping("/{id}")
    public void returnBook(@PathVariable Long id) {
        borrowService.returnBook(id);
    }
}
