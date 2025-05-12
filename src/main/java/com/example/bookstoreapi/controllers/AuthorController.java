package com.example.bookstoreapi.controllers;

import com.example.bookstoreapi.entites.AuthorEntity;
import com.example.bookstoreapi.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    private AuthorRepository repository;

    @GetMapping("/authors")
    public ResponseEntity<?> getAllAuthors(){
        try{
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            System.out.println("O erro no AuthorController --> " + e.getMessage());
            return ResponseEntity.ok("Houve um erro: ");
        }
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(repository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não existe");
        }
    }

    @PostMapping("/authors")
    public ResponseEntity<String> insertAuthor(@RequestBody AuthorEntity entity){
        try{
            repository.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Autor inserido com sucesso");
        } catch (Exception e) {
            System.out.println("Erro localizado no AuthorController --> " + e.getMessage());
            return ResponseEntity.ok("Erro ao inserir usuário no banco de dados");
        }
    }
}
