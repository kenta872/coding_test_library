package com.company.library.secondary.model;

import com.company.library.domain.model.Book;
import com.company.library.domain.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
    private long id;
    private String name;
    private List<BookEntity> bookEntityList;

    public static Employee convert(@NotNull EmployeeEntity employeeEntity) {
        final List<BookEntity> bookEntityList = employeeEntity.getBookEntityList();
        if (CollectionUtils.isEmpty(bookEntityList)) {
            return new Employee(
                    employeeEntity.getId(),
                    employeeEntity.getName(),
                    new ArrayList<>()
            );
        }

        final List<Book> bookList = BookEntity.convert(bookEntityList);
        return new Employee(
                employeeEntity.getId(),
                employeeEntity.getName(),
                bookList
        );
    }
}
