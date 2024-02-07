package com.juno.library.controller.userdto;


import lombok.Getter;

import java.util.List;

@Getter
public class PaginatedResult<T> {

    private final List<T> data;
    private final int totalPages;

    public PaginatedResult(List<T> data, int totalPages) {
        this.data = data;
        this.totalPages = totalPages;
    }
}
