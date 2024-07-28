package com.company.library.secondary.model;

import lombok.Data;

import java.util.List;

@Data
public class BorrowedBookListResultMap {
    private Long id;
    private String name;
    private List<BookEntity> bookEntityList;
}
