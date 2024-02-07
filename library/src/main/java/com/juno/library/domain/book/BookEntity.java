package com.juno.library.domain.book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookEntity {
    private final Long id;
    private final String name;
    private final String author;
    private final Long category;
    private final Boolean isRental;
}
