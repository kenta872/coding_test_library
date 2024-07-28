package com.company.library.primary.controller;

import com.company.library.primary.model.BorrowRequest;
import com.company.library.primary.service.BorrowBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrow")
@RequiredArgsConstructor
public class BorrowBookController {
    private final BorrowBookService borrowBookService;

    @PostMapping
    public void borrowBook(@Validated @RequestBody BorrowRequest request) {
        borrowBookService.borrowBook(
                request.getEmployeeId(),
                request.getBookIdList()
        );
    }
}
