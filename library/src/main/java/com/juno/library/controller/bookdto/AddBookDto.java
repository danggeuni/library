package com.juno.library.controller.bookdto;

import com.juno.library.domain.book.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddBookDto {

    private String name;
    private String author;
    private Long categoryCode;

    public BookEntity toEntity(){
       return new BookEntity(null, name, author, categoryCode, false);
    }
}
