package com.example.bookstoreapi.services;

import com.example.bookstoreapi.entites.AuthorEntity;
import com.example.bookstoreapi.exceptions.EmptyFieldsException;
import com.example.bookstoreapi.exceptions.NotAllowedValueException;
import com.example.bookstoreapi.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository repository;

    public List<AuthorEntity> getAllAuthors(){
        return repository.findAll();
    }

    public AuthorEntity getAuthorByCode(Long code) {

        return repository.findById(code).orElseThrow(()
                -> new EntityNotFoundException("Autor não encontrado"));
    }

    public void insertAuthor(String citizen, String name) throws EmptyFieldsException {

        if (citizen.isBlank() || name.isBlank())
            throw new EmptyFieldsException("Preencha todos os campos");

        AuthorEntity entity = new AuthorEntity(citizen, name);
        repository.save(entity);
    }

    public void deleteAuthor(Long code) throws Exception {

        if (code <= 0)
            throw new NotAllowedValueException("Valor não permitido");

        var entity = getAuthorByCode(code);

        if (entity == null)
            throw new EntityNotFoundException("Deleção inválida");

        repository.deleteById(code);
    }
}