package com.company.library.primary.service;

import com.company.library.domain.model.Book;
import com.company.library.domain.model.Employee;
import com.company.library.primary.exception.BusinessRuleViolationException;
import com.company.library.secondary.adapter.BookRepository;
import com.company.library.secondary.adapter.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReturnBookService {
    private final BookRepository bookRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void returnBook(long employeeId, long bookId) {
        final Employee employee = employeeRepository.loadEmployee(employeeId);
        final Book book = bookRepository.findBook(bookId);
        if (!employee.hasReturnedBook(book)) {
            throw new BusinessRuleViolationException("You are trying to return a book that was not borrowed.");
        }
        employeeRepository.returnBook(employeeId, book);
    }

    @Transactional
    public void returnAllBook(long employeeId) {
        final Employee employee = employeeRepository.loadEmployee(employeeId);
        if (!employee.hasBorrowedBooks()) {
            throw new NoSuchElementException("You have not borrowed any books.");
        }
        employeeRepository.returnAllBook(employee);
    }
}
