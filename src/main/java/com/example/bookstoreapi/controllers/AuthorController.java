package com.example.bookstoreapi.controllers;

import com.example.bookstoreapi.entites.AuthorEntity;
import com.example.bookstoreapi.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    private AuthorRepository repository;

    @GetMapping("/author")
    public List<AuthorEntity> getAllAuthors(){
        return repository.findAll();
    }

    @GetMapping("/author/{id}")
    public Optional<AuthorEntity> getAuthorById(@Param("id") Long id){
        return repository.findById(id);
    }

    @PostMapping("/author")
    public ResponseEntity<String> insertAuthor(@RequestBody AuthorEntity entity){
        repository.save(entity);
        return ResponseEntity.ok("Autor inserido com sucesso");
    }
}
