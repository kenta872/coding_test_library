package com.company.library.primary.controller;

import com.company.library.primary.model.ReturnRequest;
import com.company.library.primary.service.ReturnBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/return")
@RequiredArgsConstructor
public class ReturnBookController {
    private final ReturnBookService returnBookService;

    @PostMapping
    public void returnBook(@RequestBody ReturnRequest request) {
        returnBookService.returnBook(
                request.getEmployeeId(),
                request.getBookId()
        );
    }

    @PostMapping("/all")
    public void returnAllBook(@RequestBody ReturnRequest request) {
        returnBookService.returnAllBook(request.getEmployeeId());
    }
}
