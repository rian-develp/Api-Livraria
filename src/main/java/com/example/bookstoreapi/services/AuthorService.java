package com.example.bookstoreapi.services;

import com.example.bookstoreapi.entites.AuthorEntity;
import com.example.bookstoreapi.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository repository;

    public List<AuthorEntity> getAllAuthors(){
        return repository.findAll();
    }

    public Optional<AuthorEntity> getAuthorByCode(Long code){
        return repository.findById(code);
    }

    public boolean insertAuthor(String citizen, String name){
        if (!citizen.isEmpty() && !citizen.isBlank()
                && !name.isEmpty() && !name.isBlank()){
            AuthorEntity entity = new AuthorEntity(citizen, name);
            repository.save(entity);
            return true;
        }

        return false;
    }
}
