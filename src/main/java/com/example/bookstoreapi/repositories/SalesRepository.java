package com.example.bookstoreapi.repositories;

import com.example.bookstoreapi.entites.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<SalesEntity, Long> {
}
