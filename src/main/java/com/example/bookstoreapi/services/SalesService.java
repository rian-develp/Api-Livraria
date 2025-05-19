package com.example.bookstoreapi.services;

import com.example.bookstoreapi.entites.SalesEntity;
import com.example.bookstoreapi.repositories.SalesRepository;
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

    public Optional<SalesEntity> getSaleById(Long id){
        return repository.findById(id);
    }

    public boolean insertSale(Long bookCode, Long customerId){

        if(bookCode == null || customerId == null)
            return false;

        SalesEntity entity = new SalesEntity(bookCode, customerId);
        repository.save(entity);
        return true;
    }
}
