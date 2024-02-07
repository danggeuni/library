package com.juno.library.controller;


import com.juno.library.controller.bookdto.AddBookDto;
import com.juno.library.domain.book.BookEntity;
import com.juno.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    @PostMapping("/api/books")
    public ResponseEntity<BookEntity> addBook(@RequestBody AddBookDto dto){
        BookEntity entity = bookService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @GetMapping("/api/books")
    public ResponseEntity<List<BookEntity>> findBooks(){
        List<BookEntity> entities = bookService.findAll();

        return ResponseEntity.ok().body(entities);
    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<BookEntity> findBookById(@PathVariable Long id){

        BookEntity entity = bookService.findById(id);

        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/api/books/")
    public ResponseEntity<List<BookEntity>> findBookByName(@RequestParam String name){
        List<BookEntity> entity = bookService.findByName(name);

        return ResponseEntity.ok().body(entity);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id){
        bookService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/books/{id}/rental")
    public ResponseEntity<Void> rentalBook(@PathVariable Long id, @RequestParam Long userId){
        bookService.rentalBook(id, userId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/books/{id}/rental")
    public ResponseEntity<Void> returnBook(@PathVariable Long id){
        bookService.returnBook(id);

        return ResponseEntity.ok().build();
    }
}
