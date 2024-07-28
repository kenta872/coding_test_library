package com.company.library.infrastructure.mapper;

import com.company.library.secondary.model.BookEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookMapper {
    BookEntity findByBookId(@Param("bookId") long bookId);

    void updateBorrowedStatus(@Param("bookId") long id, @Param("isBorrowed") boolean isBorrowed);
}
