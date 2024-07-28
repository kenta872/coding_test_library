package com.company.library.domain.model;

import com.company.library.primary.exception.BusinessRuleViolationException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeTest {

    @Test
    public void canBorrowBooks_whenBookCountOver() {
        // setup
        final Book testBookA = new Book(1, "testA", false);
        final List<Book> bookList = List.of(testBookA);
        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, bookList);

        // do
        assertThrows(BusinessRuleViolationException.class, () -> {
            employee.canBorrowBooks(4);
        });
    }

    @Test
    public void canBorrowBooks_whenBorrowedBookCountOver() {
        // setup
        final Book testBookA = new Book(1, "testA", false);
        final Book testBookB = new Book(2, "testB", false);
        final Book testBookC = new Book(3, "testC", false);
        final List<Book> bookList = Arrays.asList(testBookA, testBookB, testBookC);
        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, bookList);

        // do
        assertThrows(BusinessRuleViolationException.class, () -> {
            employee.canBorrowBooks(1);
        });
    }

    @Test
    public void canBorrowBooks_whenBookCountNotOver() {
        // setup
        final Book testBookA = new Book(1, "testA", false);
        final Book testBookB = new Book(2, "testB", false);
        final List<Book> bookList = Arrays.asList(testBookA, testBookB);
        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, bookList);

        // do
        final boolean result = employee.canBorrowBooks(1);

        // assert
        assertThat(result).isTrue();
    }

    @Test
    public void borrowBooks() {
        // setup
        final Book testBookA = new Book(1, "testA", false);
        final Book testBookB = new Book(2, "testB", false);
        final Book testBookC = new Book(3, "testC", false);
        List<Book> bookList = new ArrayList<>();
        bookList.add(testBookA);
        bookList.add(testBookB);
        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, bookList);
        List<Book> testBookList = new ArrayList<>();
        testBookList.add(testBookC);

        // do
        employee.borrowBooks(testBookList);

        // assert
        assertThat(employee.getBorrowedBookList().size()).isEqualTo(3);
    }

    @Test
    public void hasReturnedBook_whenExistBook() {
        // setup
        final Book testBookA = new Book(1, "testA", false);
        final Book testBookB = new Book(2, "testB", false);
        List<Book> bookList = new ArrayList<>();
        bookList.add(testBookA);
        bookList.add(testBookB);
        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, bookList);

        // do
        final boolean result = employee.hasReturnedBook(testBookA);

        // assert
        assertThat(result).isTrue();
    }

    @Test
    public void hasReturnedBook_whenNotExistBook() {
        // setup
        final Book testBookA = new Book(1, "testA", false);
        final Book testBookB = new Book(2, "testB", false);
        final Book testBookC = new Book(3, "testC", false);
        List<Book> bookList = new ArrayList<>();
        bookList.add(testBookA);
        bookList.add(testBookB);
        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, bookList);

        // do
        final boolean result = employee.hasReturnedBook(testBookC);

        // assert
        assertThat(result).isFalse();
    }

    @Test
    public void hasBorrowedBooks_whenBookListIsEmpty() {
        // setup
        List<Book> bookList = new ArrayList<>();
        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, bookList);

        // do
        final boolean result = employee.hasBorrowedBooks();

        // assert
        assertThat(result).isFalse();
    }

    @Test
    public void hasBorrowedBooks_whenBookListIsNotEmpty() {
        // setup
        final Book testBookA = new Book(1, "testA", false);
        List<Book> bookList = new ArrayList<>();
        bookList.add(testBookA);
        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, bookList);

        // do
        final boolean result = employee.hasBorrowedBooks();

        // assert
        assertThat(result).isTrue();
    }
}
