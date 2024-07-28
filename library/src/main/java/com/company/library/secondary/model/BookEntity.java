package com.company.library.secondary.model;

import com.company.library.domain.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {
    private long id;
    private String title;
    private boolean borrowed;

    public static List<Book> convert(List<BookEntity> bookEntityList) {
        if (CollectionUtils.isEmpty(bookEntityList)) {
            return new ArrayList<>();
        }
        List<Book> bookList = new ArrayList<>();
        for (BookEntity bookEntity : bookEntityList) {
            bookList.add(new Book(
                    bookEntity.getId(),
                    bookEntity.getTitle(),
                    bookEntity.isBorrowed())
            );
        }
        return bookList;
    }
}
