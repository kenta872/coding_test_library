package com.company.library.secondary.adapter;

import com.company.library.domain.model.Book;
import com.company.library.infrastructure.mapper.BookMapper;
import com.company.library.secondary.model.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final BookMapper bookMapper;

    public Book findBook(long bookId) {
        final BookEntity bookEntity = bookMapper.findByBookId(bookId);
        if (bookEntity == null) {
            throw new NoSuchElementException("The book does not exist.");
        }
        return new Book(
                bookEntity.getId(),
                bookEntity.getTitle(),
                bookEntity.isBorrowed()
        );
    }
}
