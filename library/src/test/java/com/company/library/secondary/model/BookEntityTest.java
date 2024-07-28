package com.company.library.secondary.model;

import com.company.library.domain.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookEntityTest {

    @Test
    public void convert_whenBookEntityListIsEmpty() {
        // setup
        final List<BookEntity> bookEntityList = new ArrayList<>();

        // do
        final List<Book> result = BookEntity.convert(bookEntityList);

        // assert
        assertThat(CollectionUtils.isEmpty(result)).isTrue();
    }

    @Test
    public void convert_whenBookEntityListIsNotEmpty() {
        // setup
        final long testBookId = 1;
        final String testTitle = "testTitle";
        final BookEntity bookEntity = new BookEntity(testBookId, testTitle, false);
        final List<BookEntity> bookEntityList = Collections.singletonList(bookEntity);

        // do
        final List<Book> result = BookEntity.convert(bookEntityList);

        // assert
        assertThat(result.get(0).getId()).isEqualTo(testBookId);
        assertThat(result.get(0).getTitle()).isEqualTo(testTitle);
        assertThat(result.get(0).isBorrowed()).isFalse();
    }

}
