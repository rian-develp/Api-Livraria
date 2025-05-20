package com.example.bookstoreapi.controllers;

import com.example.bookstoreapi.entites.dtos.authordtos.InsertAuthorDTO;
import com.example.bookstoreapi.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping("/authors")
    public ResponseEntity<?> getAllAuthors() {
        try {
            return ResponseEntity.ok(service.getAllAuthors());
        } catch (Exception e) {
            System.out.println("O erro no AuthorController --> " + e.getMessage());
            return ResponseEntity.ok("Houve um erro: ");
        }
    }

    @GetMapping("/authors/{code}")
    public ResponseEntity<?> getAuthorByCode(@PathVariable("code") Long code) {
        try {
            return ResponseEntity.ok(service.getAuthorByCode(code));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/authors")
    public ResponseEntity<String> insertAuthor(@RequestBody InsertAuthorDTO dto) {
        try {
            service.insertAuthor(dto.citizen(), dto.name());
            return ResponseEntity.ok("Sucesso ao criar cliente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/authors/{code}/delete")
    public ResponseEntity<String> deleteAuthorByCode(@PathVariable("code") Long code){
        try {
            service.deleteAuthor(code);
            return ResponseEntity.ok("Sucesso ao deletar cliente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
