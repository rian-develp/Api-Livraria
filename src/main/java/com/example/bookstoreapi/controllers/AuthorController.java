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

    @GetMapping("/authors")
    public List<AuthorEntity> getAllAuthors(){
        return repository.findAll();
    }

    @GetMapping("/authors/{id}")
    public Optional<AuthorEntity> getAuthorById(@PathVariable("id") Long id){
        return repository.findById(id);
    }

    @PostMapping("/authors")
    public ResponseEntity<String> insertAuthor(@RequestBody AuthorEntity entity){
        repository.save(entity);
        return ResponseEntity.ok("Autor inserido com sucesso");
    }
}
