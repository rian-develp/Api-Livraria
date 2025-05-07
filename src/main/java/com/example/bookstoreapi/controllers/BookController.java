package com.example.bookstoreapi.controllers;

import com.example.bookstoreapi.entites.BookEntity;
import com.example.bookstoreapi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping("/book")
    public List<BookEntity> getAllBooks(){
        return repository.findAll();
    }

    @GetMapping("/book/{code}")
    public BookEntity getBookById(@PathVariable("code") Long code){
        return repository.getReferenceById(code);
    }

    @PutMapping("/book/{code}/price")
    public void updateBookPrice(@PathVariable("price") Double price, @PathVariable("code") Long code){
        repository.updateBookPrice(price, code);
    }

    @PutMapping("/book/{code}/quantity")
    public void updateBookQuantity(@PathVariable("quantity") Integer quantity, @PathVariable("code") Long code){
        repository.updateBookQuantity(quantity, code);
    }

    @PostMapping
    public void createBook(@RequestParam BookEntity bookEntity){
        repository.save(bookEntity);
    }
}
