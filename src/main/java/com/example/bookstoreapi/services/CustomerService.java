package com.example.bookstoreapi.services;

import com.example.bookstoreapi.entites.CustomerEntity;
import com.example.bookstoreapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<CustomerEntity> getAllCustomers(){
        return repository.findAll();
    }

    public Optional<CustomerEntity> getCustomerById(Long code){
        return repository.findById(code);
    }

    public boolean insertCustomer(String cpf, String email, String name){
        if (cpf.isEmpty() || cpf.isBlank() ||
                email.isEmpty() || email.isBlank() ||
                name.isEmpty() || name.isBlank()
        ) {
            return false;
        }

        CustomerEntity entity = new CustomerEntity(cpf,email, name);
        return true;
    }

    public CustomerEntity getCustomerByEmail(String email){
        return repository.getCustomerByEmail(email);
    }
}
