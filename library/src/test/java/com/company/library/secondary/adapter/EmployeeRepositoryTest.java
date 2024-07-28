package com.company.library.secondary.adapter;

import com.company.library.domain.model.Book;
import com.company.library.domain.model.Employee;
import com.company.library.infrastructure.mapper.BookMapper;
import com.company.library.infrastructure.mapper.EmployeeMapper;
import com.company.library.secondary.model.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryTest {
    @Mock
    private EmployeeMapper employeeMapper;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private EmployeeRepository employeeRepository;

    @Test
    public void loadEmployee_whenNotExistEmployee() {
        // setup
        final long testEmployeeId = 1;

        // when
        Mockito.when(employeeMapper.findEmployeeByEmployeeId(testEmployeeId)).thenReturn(null);

        // do
        assertThrows(NoSuchElementException.class, () -> {
            employeeRepository.loadEmployee(testEmployeeId);
        });
    }

    @Test
    public void loadEmployee_whenExistEmployee() {
        // setup
        final long testEmployeeId = 1;
        final String testName = "testName";
        final EmployeeEntity employeeEntity = new EmployeeEntity(testEmployeeId, testName, new ArrayList<>());

        // when
        Mockito.when(employeeMapper.findEmployeeByEmployeeId(testEmployeeId)).thenReturn(employeeEntity);

        // do
        final Employee result = employeeRepository.loadEmployee(testEmployeeId);

        // assert
        assertThat(result.getId()).isEqualTo(testEmployeeId);
        assertThat(result.getName()).isEqualTo(testName);
        assertThat(CollectionUtils.isEmpty(result.getBorrowedBookList())).isTrue();
    }

    @Test
    public void borrowBook_whenEmptyBookList() {
        // setup
        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, new ArrayList<>());

        // verify
        verify(employeeMapper, times(0)).insertBorrowedBook(anyLong(), anyLong());
        verify(bookMapper, times(0)).updateBorrowedStatus(anyLong(), anyBoolean());

        // do
        employeeRepository.borrowBook(employee);
    }

    @Test
    public void borrowBook_whenNotEmptyBookList() {
        // setup
        final long testBookId = 100;
        final String testTitle = "testTitle";
        final Book book = new Book(testBookId, testTitle, false);
        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, Collections.singletonList(book));

        // when
        doNothing().when(employeeMapper).insertBorrowedBook(testEmployeeId, testBookId);
        doNothing().when(bookMapper).updateBorrowedStatus(testBookId, true);

        // do
        employeeRepository.borrowBook(employee);
    }

    @Test
    public void returnBook() {
        // setup
        final long testBookId = 100;
        final String testTitle = "testTitle";
        final Book book = new Book(testBookId, testTitle, false);
        final long testEmployeeId = 1;

        // when
        doNothing().when(employeeMapper).deleteBorrowedBook(testEmployeeId, testBookId);
        doNothing().when(bookMapper).updateBorrowedStatus(testBookId, false);

        // do
        employeeRepository.returnBook(testEmployeeId, book);
    }

    @Test
    public void returnAllBook_whenEmptyBookList() {
        // setup
        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, new ArrayList<>());

        // when
        doNothing().when(employeeMapper).deleteBorrowedBookByEmployeeId(testEmployeeId);

        // verify
        verify(bookMapper, times(0)).updateBorrowedStatus(anyLong(), anyBoolean());

        // do
        employeeRepository.returnAllBook(employee);
    }

    @Test
    public void returnAllBook_whenNotEmptyBookList() {
        // setup
        final long testBookId = 100;
        final String testTitle = "testTitle";
        final Book book = new Book(testBookId, testTitle, false);
        final long testEmployeeId = 1;
        final String testName = "testName";
        final Employee employee = new Employee(testEmployeeId, testName, Collections.singletonList(book));

        // when
        doNothing().when(employeeMapper).deleteBorrowedBookByEmployeeId(testEmployeeId);
        doNothing().when(bookMapper).updateBorrowedStatus(testBookId, false);

        // do
        employeeRepository.returnAllBook(employee);
    }
}
