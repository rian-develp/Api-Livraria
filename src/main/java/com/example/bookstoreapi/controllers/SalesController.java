package com.example.bookstoreapi.controllers;

import com.example.bookstoreapi.entites.SalesEntity;
import com.example.bookstoreapi.entites.dtos.salesdtos.InsertSaleDTO;
import com.example.bookstoreapi.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SalesController {

    @Autowired
    private SalesRepository repository;

    @GetMapping("/sales")
    public ResponseEntity<?> getAllSales(){
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            System.out.println("Erro Localizado no SalesController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro");
        }
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<?> getSaleById(@PathVariable("id") Long id){
        try {
            var sale = repository.findById(id);
            if (sale.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada");
            return ResponseEntity.ok(sale);
        } catch (Exception e) {
            System.out.println("Erro localizado no SalesController --> " +e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um Erro");
        }
    }

    @PostMapping("/sales")
    public ResponseEntity<String> insertSale(@RequestBody InsertSaleDTO dto){
        try{
            if (dto.bookCode() == null || dto.customerId() == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos estão incorretos");
            }

            SalesEntity entity = new SalesEntity();
            entity.setBookCode(dto.bookCode());
            entity.setCustomerId(dto.customerId());
            repository.save(entity);
            return ResponseEntity.ok("Venda inserida com sucesso");
        } catch (Exception e){
            System.out.println("Erro localizado no SalesController --> " +e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um Erro");
        }
    }

    @DeleteMapping("/sales/{id}/delete")
    public ResponseEntity<String> deleteSaleById(Long id){
        try {
            repository.deleteById(id);
            return ResponseEntity.ok("Venda deletada com sucesso");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir venda");
        }
    }
}
