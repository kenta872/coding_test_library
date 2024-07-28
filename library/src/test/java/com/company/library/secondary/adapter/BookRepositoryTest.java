package com.company.library.secondary.adapter;

import com.company.library.domain.model.Book;
import com.company.library.infrastructure.mapper.BookMapper;
import com.company.library.secondary.model.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BookRepositoryTest {
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private BookRepository bookRepository;

    @Test
    public void findBook_whenNotExistBook() {
        // setup
        final long testBookId = 1;

        // when
        Mockito.when(bookMapper.findByBookId(testBookId)).thenReturn(null);

        // do
        assertThrows(NoSuchElementException.class, () -> {
            bookRepository.findBook(testBookId);
        });
    }

    @Test
    public void findBook_whenExistBook() {
        // setup
        final long testBookId = 1;
        final String testTitle = "testTitle";
        final BookEntity bookEntity = new BookEntity(testBookId, testTitle, false);

        // when
        Mockito.when(bookMapper.findByBookId(testBookId)).thenReturn(bookEntity);

        // do
        final Book book = bookRepository.findBook(testBookId);

        // assert
        assertThat(book.getId()).isEqualTo(testBookId);
        assertThat(book.getTitle()).isEqualTo(testTitle);
        assertThat(book.isBorrowed()).isFalse();
    }
}
