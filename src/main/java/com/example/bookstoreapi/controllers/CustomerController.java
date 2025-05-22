package com.example.bookstoreapi.controllers;

import com.example.bookstoreapi.entites.CustomerEntity;
import com.example.bookstoreapi.entites.dtos.customerdtos.InsertCustomerDTO;
import com.example.bookstoreapi.exceptions.NotAllowedValueException;
import com.example.bookstoreapi.services.CustomerService;
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
            var optional = service.getCustomerById(id);
            return optional.isPresent() ? ResponseEntity.ok(optional.get())
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não existe");
        } catch (NotAllowedValueException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor não permitido");
        }
    }

    @GetMapping("/customers/email/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable("email") String email){
        try {
            var entity = service.getCustomerByEmail(email);

            if (entity != null)
                return ResponseEntity.ok(entity);
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe cliente com esse email");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não existe cliente com esse email");
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<?> insertCustomer(@RequestBody InsertCustomerDTO dto){
        try{
            var result = service.insertCustomer(dto.cpf(), dto.email(), dto.name());
            return result ? ResponseEntity.ok("Sucesso ao inserir cliente no banco de dados")
                    : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos inválidos");
        } catch (Exception e) {
            System.out.println("Erro localizado no CustomerController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro");
        }
    }
}
