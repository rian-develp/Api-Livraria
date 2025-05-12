package com.example.bookstoreapi.controllers;

import com.example.bookstoreapi.entites.BookEntity;
import com.example.bookstoreapi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks(){
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            System.out.println("Erro Localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro");
        }
    }

    @GetMapping("/books/{code}")
    public ResponseEntity<?> getBookById(@PathVariable("code") Long code){
        try {
            return ResponseEntity.ok(repository.findById(code));
        } catch (Exception e) {
            System.out.println("Erro Localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro");
        }
    }

    @PatchMapping("/books/{code}/{price}")
    public ResponseEntity<?> updateBookPrice(@PathVariable("price") Double price, @PathVariable("code") Long code){
        try{
            repository.updateBookPrice(price, code);
            return ResponseEntity.ok("Preço do livro atualizado com sucesso");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        } catch (Exception e) {
            System.out.println("Erro Localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar preço do livro");
        }
    }

    @PatchMapping("/books/{code}/{quantity}")
    public ResponseEntity<?> updateBookQuantity(@PathVariable("quantity") Integer quantity, @PathVariable("code") Long code){
        try{
            repository.updateBookQuantity(quantity, code);
            return ResponseEntity.ok("Quantidade do livro atualizado com sucesso");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        } catch (Exception e) {
            System.out.println("Erro Localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar quantidade do livro");
        }
    }

    @PostMapping
    public ResponseEntity<?> insertBook(@RequestBody BookEntity entity){
        try{
            repository.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Autor inserido com sucesso");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Erro localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Livro já existe no banco de dados");
        } catch (NullPointerException e){
            System.out.println("Erro Localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir livro");
        }
    }
}
