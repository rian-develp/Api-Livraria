package com.example.bookstoreapi.services;

import com.example.bookstoreapi.entites.BookEntity;
import com.example.bookstoreapi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<BookEntity> getAllBooks(){
        return repository.findAll();
    }

    public Optional<BookEntity> getBookByCode(Long code){
        return repository.findById(code);
    }

    public boolean insertBook(
        String authorName, Double price,
        Date publishDate, Integer quantity,
        String title
    ){
        if( authorName == null || authorName.isBlank() ||
            title == null || title.isBlank() ||
            price == null || publishDate == null ||
            quantity == null
        ){
            return false;
        }

        BookEntity entity = new BookEntity(authorName, price, publishDate, quantity, title);
        repository.save(entity);
        return true;
    }
}
