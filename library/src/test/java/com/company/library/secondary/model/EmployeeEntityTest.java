package com.company.library.secondary.model;

import com.company.library.domain.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EmployeeEntityTest {

    @Test
    public void convert_whenBookEntityListIsEmpty() {
        // setup
        final long testId = 1;
        final String testName = "testName";
        final List<BookEntity> bookEntityList = new ArrayList<>();
        final EmployeeEntity employeeEntity = new EmployeeEntity(testId, testName, bookEntityList);

        // do
        final Employee result = EmployeeEntity.convert(employeeEntity);

        // assert
        assertThat(result.getId()).isEqualTo(testId);
        assertThat(result.getName()).isEqualTo(testName);
        assertThat(CollectionUtils.isEmpty(result.getBorrowedBookList())).isTrue();
    }

    @Test
    public void convert_whenBookEntityListIsNotEmpty() {
        // setup
        final long testBookId = 100;
        final String testTitle = "testTitle";
        final long testEmployeeId = 1;
        final String testName = "testName";
        final BookEntity bookEntity = new BookEntity(testBookId, testTitle, false);
        final List<BookEntity> bookEntityList = Collections.singletonList(bookEntity);
        final EmployeeEntity employeeEntity = new EmployeeEntity(testEmployeeId, testName, bookEntityList);

        // do
        final Employee result = EmployeeEntity.convert(employeeEntity);

        // assert
        assertThat(result.getId()).isEqualTo(testEmployeeId);
        assertThat(result.getName()).isEqualTo(testName);
        assertThat(result.getBorrowedBookList().get(0).getId()).isEqualTo(testBookId);
        assertThat(result.getBorrowedBookList().get(0).getTitle()).isEqualTo(testTitle);
    }
}
