package com.example.bookstoreapi.repositories;

import com.example.bookstoreapi.entites.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
