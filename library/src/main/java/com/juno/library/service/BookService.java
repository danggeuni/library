package com.juno.library.service;

import com.juno.library.controller.bookdto.AddBookDto;
import com.juno.library.controller.userdto.PaginatedResult;
import com.juno.library.domain.book.BookEntity;
import com.juno.library.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookEntity save(AddBookDto dto){
        return bookRepository.save(dto.toEntity());
    }

    public List<BookEntity> findAll(){
        return bookRepository.findAll();
    }

    public BookEntity findById(Long id){
        return bookRepository.findById(id);
    }

    public List<BookEntity> findByName(String name){
        return bookRepository.findByName(name);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }

    public void rentalBook(Long bookId, Long userId){
        bookRepository.rentalBook(bookId, userId);
    }

    public void returnBook(Long booId){
        bookRepository.returnBook(booId);
    }

    public PaginatedResult<BookEntity> paging(String searchQuery, int pageSize, int pageNumber){

        List<BookEntity> data = bookRepository.paging(searchQuery, pageSize, pageNumber);
        int totalPage = bookRepository.getTotalPage(searchQuery, pageSize);

        return new PaginatedResult<>(data, totalPage);
    }
}
