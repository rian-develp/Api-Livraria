package com.example.bookstoreapi.controllers;

import com.example.bookstoreapi.entites.BookEntity;
import com.example.bookstoreapi.entites.dtos.bookdtos.InsertBookDTO;
import com.example.bookstoreapi.entites.dtos.bookdtos.UpdateBookPriceDTO;
import com.example.bookstoreapi.entites.dtos.bookdtos.UpdateBookQuantity;
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
            var bookEntity = repository.findById(code);
            if (bookEntity.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
            }
            return ResponseEntity.ok(bookEntity);
        } catch (Exception e) {
            System.out.println("Erro Localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro");
        }
    }

    @PatchMapping("/books")
    public ResponseEntity<?> updateBookPrice(@RequestBody UpdateBookPriceDTO dto){
        try{
            if (dto.id() != null && dto.price() != null){
                repository.updateBookPrice(dto.price(), dto.id());
                return ResponseEntity.ok("Preço do livro atualizado com sucesso");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar preço do livro");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        } catch (Exception e) {
            System.out.println("Erro Localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar preço do livro");
        }
    }

    @PatchMapping("/books/{code}/{quantity}")
    public ResponseEntity<?> updateBookQuantity(@RequestBody UpdateBookQuantity dto){
        try{
            if (dto.id() != null && dto.quantity() != null){
                repository.updateBookQuantity(dto.quantity(), dto.id());
                return ResponseEntity.ok("Quantidade do livro atualizado com sucesso");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar livro");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        } catch (Exception e) {
            System.out.println("Erro Localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar quantidade do livro");
        }
    }

    @PostMapping
    public ResponseEntity<?> insertBook(@RequestBody InsertBookDTO dto){
        try{
            if (dto.authorName() == null || dto.authorName().isBlank() ||
                    dto.title() == null || dto.title().isBlank() ||
                    dto.price() == null || dto.publishDate() == null ||
                    dto.quantity() == null
            ) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos estão incorretos");
            }

            BookEntity entity = new BookEntity();
            entity.setAuthorName(dto.authorName());
            entity.setPrice(dto.price());
            entity.setPublishDate(dto.publishDate());
            entity.setQuantity(dto.quantity());
            entity.setTitle(dto.title());

            return ResponseEntity.status(HttpStatus.CREATED).body("Livro criado com sucesso");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Erro localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Livro já existe no banco de dados");
        } catch (NullPointerException e){
            System.out.println("Erro Localizado no BookController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir livro");
        }
    }
}
