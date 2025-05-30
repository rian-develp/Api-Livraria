package com.example.bookstoreapi.services;

import com.example.bookstoreapi.entites.AuthorEntity;
import com.example.bookstoreapi.exceptions.EmptyFieldsException;
import com.example.bookstoreapi.exceptions.NotAllowedValueException;
import com.example.bookstoreapi.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Optional<AuthorEntity> getAuthorByCode(Long code) throws EmptyFieldsException {

        if (code == null)
            throw new EmptyFieldsException("É necessário inserir o código");

        return repository.findById(code);
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

        if (entity.isEmpty())
            throw new EntityNotFoundException("Deleção inválida");

        repository.deleteById(code);
    }
}