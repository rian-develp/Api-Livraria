package com.example.bookstoreapi.controllers;

import com.example.bookstoreapi.entites.dtos.bookdtos.InsertBookDTO;
import com.example.bookstoreapi.entites.dtos.bookdtos.UpdateBookPriceDTO;
import com.example.bookstoreapi.entites.dtos.bookdtos.UpdateBookQuantity;
import com.example.bookstoreapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks(){
        try {
            return ResponseEntity.ok(service.getAllBooks());
        } catch (Exception e) {
            System.out.println("Erro Localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro");
        }
    }

    @GetMapping("/books/{code}")
    public ResponseEntity<?> getBookByCode(@PathVariable("code") Long code){
        try {
            var bookEntity = service.getBookByCode(code);
            if (bookEntity.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
            }
            return ResponseEntity.ok(bookEntity);
        } catch (Exception e) {
            System.out.println("Erro Localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro");
        }
    }

    @PatchMapping("/books/price")
    public ResponseEntity<?> updateBookPrice(@RequestBody UpdateBookPriceDTO dto){
        try{
            var result = service.updateBookPrice(dto.price(), dto.id());
            return result ? ResponseEntity.ok("Preço do livro atualizado com sucesso")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        } catch (Exception e) {
            System.out.println("Erro Localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar preço do livro");
        }
    }

    @PatchMapping("/books/quantity")
    public ResponseEntity<?> updateBookQuantity(@RequestBody UpdateBookQuantity dto){
        try{
            var result = service.updateBookQuantity(dto.quantity(), dto.id());
            return result ? ResponseEntity.ok("Preço do livro atualizado com sucesso")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        } catch (Exception e) {
            System.out.println("Erro Localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar quantidade do livro");
        }
    }

    @PostMapping("/books")
    public ResponseEntity<?> insertBook(@RequestBody InsertBookDTO dto){
        try{
            var result = service.insertBook(dto.authorName(), dto.price(), dto.publishDate(), dto.quantity(), dto.title());
            return result ? ResponseEntity.status(HttpStatus.CREATED).body("Livro criado com sucesso")
                    : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos estão incorretos");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Erro localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Livro já existe no banco de dados");
        } catch (NullPointerException e){
            System.out.println("Erro Localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir livro");
        }
    }
}
