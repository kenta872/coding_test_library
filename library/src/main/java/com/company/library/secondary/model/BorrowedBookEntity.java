package com.company.library.secondary.model;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class BorrowedBookEntity {
    private Long id;
    @NotNull
    private Long bookId;
    @NotNull
    private Long employeeId;

    public BorrowedBookEntity(@NotNull Long bookId, @NotNull Long employeeId) {
        this.bookId = bookId;
        this.employeeId = employeeId;
    }
}
