package com.company.library.infrastructure.mapper;

import com.company.library.secondary.model.EmployeeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeMapper {
    EmployeeEntity findEmployeeByEmployeeId(@Param("employeeId") long employeeId);

    void insertBorrowedBook(@Param("employeeId") long employeeId, @Param("bookId") long bookId);

    void deleteBorrowedBook(@Param("employeeId") long employeeId, @Param("bookId") long bookId);

    void deleteBorrowedBookByEmployeeId(@Param("employeeId") long employeeId);
}
