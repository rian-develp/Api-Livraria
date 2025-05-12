package com.example.bookstoreapi.controllers;

import com.example.bookstoreapi.entites.CustomerEntity;
import com.example.bookstoreapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/customers")
    public List<CustomerEntity> getAllCustomers(){
        return repository.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id){
        return repository.findById(id)
                .map(customer -> ResponseEntity.ok().body((Object) customer))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("O cliente não existe"));
    }

    @GetMapping("/customers/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable("email") String email){
        try {
            return ResponseEntity.ok(repository.getCustomerByEmail(email));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Não existe cliente com esse email");
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<?> insertCustomer(@RequestBody CustomerEntity entity){
        repository.save(entity);
        return ResponseEntity.ok("Sucesso ao inserir cliente no banco de dados");
    }
}
