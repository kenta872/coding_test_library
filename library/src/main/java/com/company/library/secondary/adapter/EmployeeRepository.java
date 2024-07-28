package com.company.library.secondary.adapter;

import com.company.library.domain.model.Book;
import com.company.library.domain.model.Employee;
import com.company.library.infrastructure.mapper.BookMapper;
import com.company.library.infrastructure.mapper.EmployeeMapper;
import com.company.library.secondary.model.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final EmployeeMapper employeeMapper;
    private final BookMapper bookMapper;

    public Employee loadEmployee(long employeeId) {
        final EmployeeEntity employeeEntity = employeeMapper.findEmployeeByEmployeeId(employeeId);
        if (employeeEntity == null) {
            throw new NoSuchElementException("The employee does not exist.");
        }
        return EmployeeEntity.convert(employeeEntity);
    }

    public void borrowBook(Employee employee) {
        final List<Book> bookList = employee.getBorrowedBookList();
        for (Book book : bookList) {
            employeeMapper.insertBorrowedBook(
                    employee.getId(),
                    book.getId()
            );
            bookMapper.updateBorrowedStatus(
                    book.getId(),
                    true
            );
        }
    }

    public void returnBook(long employeeId, Book book) {
        employeeMapper.deleteBorrowedBook(
                employeeId,
                book.getId()
        );
        bookMapper.updateBorrowedStatus(
                book.getId(),
                false
        );
    }

    public void returnAllBook(Employee employee) {
        final List<Book> bookList = employee.getBorrowedBookList();
        employeeMapper.deleteBorrowedBookByEmployeeId(employee.getId());
        for (Book book : bookList) {
            bookMapper.updateBorrowedStatus(
                    book.getId(),
                    false
            );
        }
    }
}
