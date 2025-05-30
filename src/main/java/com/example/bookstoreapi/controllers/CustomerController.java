package com.example.bookstoreapi.controllers;

import com.example.bookstoreapi.entites.CustomerEntity;
import com.example.bookstoreapi.entites.dtos.customerdtos.InsertCustomerDTO;
import com.example.bookstoreapi.exceptions.EmptyFieldsException;
import com.example.bookstoreapi.exceptions.NotAllowedValueException;
import com.example.bookstoreapi.services.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/customers")
    public List<CustomerEntity> getAllCustomers(){
        return service.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(service.getCustomerById(id));
        } catch (NotAllowedValueException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor não permitido");
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
    }

    @GetMapping("/customers/email/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable("email") String email){
        try {
            var entity = service.getCustomerByEmail(email);
            return ResponseEntity.ok(entity);
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não existe cliente com esse email");
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<?> insertCustomer(@RequestBody InsertCustomerDTO dto){
        try{
            service.insertCustomer(dto.cpf(), dto.email(), dto.name());
            return ResponseEntity.ok("Sucesso ao inserir cliente no banco de dados");
        } catch (EmptyFieldsException | NotAllowedValueException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro localizado no CustomerController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro");
        }
    }
}
