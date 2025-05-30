package com.example.bookstoreapi.services;

import com.example.bookstoreapi.entites.SalesEntity;
import com.example.bookstoreapi.exceptions.NotAllowedValueException;
import com.example.bookstoreapi.repositories.SalesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesService {

    @Autowired
    private SalesRepository repository;

    public List<SalesEntity> getAllSales(){
        return repository.findAll();
    }

    public Optional<SalesEntity> getSaleById(Long id) throws Exception{

        if (id < 0)
            throw new EntityNotFoundException("Venda não existe");
        else if (id == null)
            throw new NullPointerException("Id não pode ser null");

        return repository.findById(id);
    }

    public void insertSale(Long bookCode, Long customerId) throws Exception{

        if(bookCode == null || customerId == null)
            throw new NullPointerException("Valores não podem ser null");

        if (bookCode < 0 || customerId < 0)
            throw new NotAllowedValueException("Valores devem ser preenchidos");

        SalesEntity entity = new SalesEntity(bookCode, customerId);
        repository.save(entity);
    }
}
