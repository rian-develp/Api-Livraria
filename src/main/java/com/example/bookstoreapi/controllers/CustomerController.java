package com.example.bookstoreapi.controllers;

import com.example.bookstoreapi.entites.CustomerEntity;
import com.example.bookstoreapi.entites.dtos.customerdtos.InsertCustomerDTO;
import com.example.bookstoreapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não existe cliente com esse email");
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<?> insertCustomer(@RequestBody InsertCustomerDTO dto){
        try{
            if (dto.cpf().isEmpty() || dto.cpf().isBlank() ||
                    dto.email().isEmpty() || dto.email().isBlank() ||
                    dto.name().isEmpty() || dto.name().isBlank()
            ) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos estão incorretos");
            }

            CustomerEntity entity = new CustomerEntity();
            entity.setCpf(dto.cpf());
            entity.setEmail(dto.email());
            entity.setName(dto.name());
            repository.save(entity);
            return ResponseEntity.ok("Sucesso ao inserir cliente no banco de dados");
        } catch (Exception e) {
            System.out.println("Erro localizado no CustomerController --> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Houve um erro");
        }
    }
}
