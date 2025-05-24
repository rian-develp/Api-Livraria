package com.example.bookstoreapi.services;

import com.example.bookstoreapi.entites.CustomerEntity;
import com.example.bookstoreapi.exceptions.EmptyFieldsException;
import com.example.bookstoreapi.exceptions.NotAllowedValueException;
import com.example.bookstoreapi.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Optional<CustomerEntity> getCustomerById(Long id) throws NotAllowedValueException{

        if (id == null || id <= 0)
            throw new NotAllowedValueException("Valor não permitido");

        var entity = repository.findById(id);

        if (entity.isEmpty())
            throw new EntityNotFoundException("Não existe cliente com esse identificador");
        else
            return entity;
    }

    public boolean insertCustomer(String cpf, String email, String name) throws Exception{
        if (cpf.isBlank() || email.isBlank() || name.isBlank()) {
            throw new EmptyFieldsException("Todos os campos devem ser preenchidos");
        }

        if (!name.matches("/^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$/")
            || !cpf.matches("/^[0-9]+$/")
            || !email.matches("/^([a-z]){1,}([a-z0-9._-]){1,}([@]){1}([a-z]){2,}([.]){1}([a-z]){2,}([.]?){1}([a-z]?){2,}$/")
        ){
            throw new NotAllowedValueException("Valores não permitidos");
        }

        CustomerEntity entity = new CustomerEntity(cpf, email, name);
        repository.save(entity);
        return true;
    }

    public CustomerEntity getCustomerByEmail(String email) {

        var entity = repository.getCustomerByEmail(email);

        if (entity != null)
            return entity;
        else
            throw new EntityNotFoundException("Não existe cliente com esse Email");
    }
}
