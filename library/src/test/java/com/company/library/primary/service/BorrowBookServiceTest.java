package com.company.library.primary.service;

import com.company.library.domain.model.Book;
import com.company.library.domain.model.Employee;
import com.company.library.primary.exception.BusinessRuleViolationException;
import com.company.library.primary.exception.NoBooksAvailableExceptionException;
import com.company.library.secondary.adapter.BookRepository;
import com.company.library.secondary.adapter.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class BorrowBookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private BorrowBookService borrowBookService;

    @Test
    public void borrowBook_whenBorrowedBookOver() {
        // setup
        final long testEmployeeId = 1;
        final String testName = "testName";
        final List<Long> testBookIdList = Arrays.asList(1L, 2L, 3L, 4L);
        final Employee employee = new Employee(testEmployeeId, testName, new ArrayList<>());

        // verify
        Mockito.when(employeeRepository.loadEmployee(testEmployeeId)).thenReturn(employee);

        // do
        assertThrows(BusinessRuleViolationException.class, () -> {
            borrowBookService.borrowBook(testEmployeeId, testBookIdList);
        });
    }

    @Test
    public void borrowBook_whenBorrowedBookNotExist() {
        // setup
        final long testEmployeeId = 1;
        final String testName = "testName";
        final List<Long> testBookIdList = Arrays.asList(1L, 2L);
        final long testBookId = 2;
        final String testTitle = "testTitle";
        final Book book = new Book(testBookId, testTitle, true);
        final Employee employee = new Employee(testEmployeeId, testName, Collections.singletonList(book));

        // verify
        Mockito.when(employeeRepository.loadEmployee(testEmployeeId)).thenReturn(employee);
        Mockito.when(bookRepository.findBook(anyLong())).thenReturn(new Book(3, "testTitleB", true));

        // do
        assertThrows(NoBooksAvailableExceptionException.class, () -> {
            borrowBookService.borrowBook(testEmployeeId, testBookIdList);
        });
    }

    @Test
    public void borrowBook_whenBorrowedBookExist() {
        // setup
        final long testBookId = 1;
        final String testTitle = "testTitle";
        final Book book = new Book(testBookId, testTitle, true);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        final long testEmployeeId = 2;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, bookList);
        final List<Long> testBookIdList = new ArrayList<>();
        testBookIdList.add(1L);
        testBookIdList.add(2L);

        // verify
        Mockito.when(employeeRepository.loadEmployee(testEmployeeId)).thenReturn(employee);
        Mockito.when(bookRepository.findBook(anyLong())).thenReturn(new Book(3, "testTitleB", false));
        doNothing().when(employeeRepository).borrowBook(employee);

        // do
        borrowBookService.borrowBook(testEmployeeId, testBookIdList);
    }
}
