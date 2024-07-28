package com.company.library.primary.service;

import com.company.library.domain.model.Book;
import com.company.library.domain.model.Employee;
import com.company.library.primary.exception.BusinessRuleViolationException;
import com.company.library.primary.exception.NoBooksAvailableExceptionException;
import com.company.library.secondary.adapter.BookRepository;
import com.company.library.secondary.adapter.EmployeeRepository;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowBookService {
    private final BookRepository bookRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void borrowBook(long employeeId, @NotEmpty List<Long> bookIdList) {
        final Employee employee = employeeRepository.loadEmployee(employeeId);
        if (!employee.canBorrowBooks(bookIdList.size())) {
            throw new BusinessRuleViolationException("The borrowing limit for books has been exceeded, so you cannot borrow any more.");
        }
        final List<Book> bookList = new ArrayList<>();
        for (long bookId : bookIdList) {
            final Book book = bookRepository.findBook(bookId);
            if (book != null && !book.isBorrowed()) {
                bookList.add(book);
            }
        }
        if (CollectionUtils.isEmpty(bookList)) {
            throw new NoBooksAvailableExceptionException("There are no books available for borrowing.");
        }
        employee.borrowBooks(bookList);
        employeeRepository.borrowBook(employee);
    }
}
