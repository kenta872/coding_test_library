package com.company.library.primary.service;

import com.company.library.domain.model.Book;
import com.company.library.domain.model.Employee;
import com.company.library.primary.exception.BusinessRuleViolationException;
import com.company.library.secondary.adapter.BookRepository;
import com.company.library.secondary.adapter.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class ReturnBookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private ReturnBookService returnBookService;

    @Test
    public void returnBook_whenHasNotReturnedBook() {
        // setup
        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, new ArrayList<>());

        final long testBookId = 10;
        final String testTitle = "testTitle";
        final Book book = new Book(testBookId, testTitle, false);

        // verify
        Mockito.when(employeeRepository.loadEmployee(testEmployeeId)).thenReturn(employee);
        Mockito.when(bookRepository.findBook(testBookId)).thenReturn(book);

        // do
        assertThrows(BusinessRuleViolationException.class, () -> {
            returnBookService.returnBook(testEmployeeId, testBookId);
        });
    }

    @Test
    public void returnBook_whenHasReturnedBook() {
        // setup
        final long testBookId = 10;
        final String testTitle = "testTitle";
        final Book book = new Book(testBookId, testTitle, false);

        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, Collections.singletonList(book));

        // verify
        Mockito.when(employeeRepository.loadEmployee(testEmployeeId)).thenReturn(employee);
        Mockito.when(bookRepository.findBook(testBookId)).thenReturn(book);
        doNothing().when(employeeRepository).returnBook(testEmployeeId, book);

        // do
        returnBookService.returnBook(testEmployeeId, testBookId);
    }


    @Test
    public void returnAllBook_whenHasNotBorrowedBook() {
        // setup
        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, new ArrayList<>());


        // verify
        Mockito.when(employeeRepository.loadEmployee(testEmployeeId)).thenReturn(employee);

        // do
        assertThrows(NoSuchElementException.class, () -> {
            returnBookService.returnAllBook(testEmployeeId);
        });
    }

    @Test
    public void returnAllBook_whenHasBorrowedBook() {
        // setup
        final long testBookId = 10;
        final String testTitle = "testTitle";
        final Book book = new Book(testBookId, testTitle, false);

        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, Collections.singletonList(book));


        // verify
        Mockito.when(employeeRepository.loadEmployee(testEmployeeId)).thenReturn(employee);
        doNothing().when(employeeRepository).returnAllBook(employee);

        // do
        returnBookService.returnAllBook(testEmployeeId);
    }
}
