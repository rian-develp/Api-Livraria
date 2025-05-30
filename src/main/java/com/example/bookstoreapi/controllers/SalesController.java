package com.example.bookstoreapi.controllers;

import com.example.bookstoreapi.entites.dtos.salesdtos.InsertSaleDTO;
import com.example.bookstoreapi.exceptions.NotAllowedValueException;
import com.example.bookstoreapi.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SalesController {

    @Autowired
    private SalesService service;

    @GetMapping("/sales")
    public ResponseEntity<?> getAllSales(){
        try {
            return ResponseEntity.ok(service.getAllSales());
        } catch (Exception e) {
            System.out.println("Erro Localizado no SalesController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro");
        }
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<?> getSaleById(@PathVariable("id") Long id){
        try {
            var optional = service.getSaleById(id);
            if (optional.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada");
            return ResponseEntity.ok(optional);
        } catch (Exception e) {
            System.out.println("Erro localizado no SalesController --> " +e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um Erro");
        }
    }

    @PostMapping("/sales")
    public ResponseEntity<String> insertSale(@RequestBody InsertSaleDTO dto){
        try{
          service.insertSale(dto.bookCode(), dto.customerId());
          return ResponseEntity.ok("Venda inserida com sucesso");
        } catch (NullPointerException | NotAllowedValueException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            System.out.println("Erro localizado no SalesController --> " +e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um Erro");
        }
    }
}
