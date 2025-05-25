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
    public ResponseEntity<?> getAuthorById(@PathVariable("code") Long code) {

        try {
            var entity = service.getAuthorByCode(code);
            if (entity.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Autor não encontrado");

            return ResponseEntity.ok(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro");
        }
    }

    @PostMapping("/authors")
    public ResponseEntity<String> insertAuthor(@RequestBody InsertAuthorDTO dto) {

        if (!dto.citizen().isEmpty() && !dto.citizen().isBlank()
                && !dto.name().isEmpty() && !dto.name().isBlank()
        ) {

            try {
                service.insertAuthor(dto.citizen(), dto.name());
                return ResponseEntity.status(HttpStatus.CREATED).body("Autor inserido com sucesso");
            } catch (Exception e) {
                System.out.println("Erro localizado no AuthorController --> " + e.getMessage());
                return ResponseEntity.ok("Erro ao inserir usuário no banco de dados");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro nos campos");
        }
    }
}
