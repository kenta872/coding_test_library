package com.company.library.domain.model;

import com.company.library.primary.exception.BusinessRuleViolationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private long id;
    private String name;
    private List<Book> borrowedBookList;

    public boolean canBorrowBooks(int bookCount) {
        if (bookCount > 3) {
            throw new BusinessRuleViolationException("You have exceeded the maximum number of books that can be borrowed at one time.");
        }
        final int totalCount = borrowedBookList.size() + bookCount;
        if (totalCount > 3) {
            throw new BusinessRuleViolationException("The borrowing limit for books has been exceeded, so you cannot borrow any more.");
        }
        return true;
    }

    public void borrowBooks(List<Book> bookList) {
        if (canBorrowBooks(bookList.size())) {
            this.borrowedBookList.addAll(bookList);
        }
    }

    public boolean hasReturnedBook(Book book) {
        return borrowedBookList.stream().anyMatch(borrowedBook -> borrowedBook.getId() == book.getId());
    }

    public boolean hasBorrowedBooks() {
        return !borrowedBookList.isEmpty();
    }
}
