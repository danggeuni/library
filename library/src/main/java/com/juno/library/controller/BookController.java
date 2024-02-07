package com.juno.library.controller;

import com.juno.library.controller.userdto.PaginatedResult;
import com.juno.library.domain.book.BookEntity;
import com.juno.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public String showBookPage(@RequestParam(required = false) String searchQuery, @RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "1") int pageNumber, Model model){

        PaginatedResult<BookEntity> result = bookService.paging(searchQuery, pageSize, pageNumber);

        model.addAttribute("data", result.getData());
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("searchQuery", searchQuery);

        return "bookList";
    }

    @GetMapping("/books/{id}")
    public String showBook(@PathVariable Long id, Model model){
        BookEntity entity = bookService.findById(id);
        model.addAttribute("book", entity);

        return "book";
    }
}
