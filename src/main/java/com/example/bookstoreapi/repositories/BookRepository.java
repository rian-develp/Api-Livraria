package com.example.bookstoreapi.repositories;

import com.example.bookstoreapi.entites.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE books SET price = :newPrice WHERE code = :code", nativeQuery = true)
    void updateBookPrice(@Param("newPrice") Double newPrice, @Param("code") Long code);

    @Modifying
    @Transactional
    @Query(value = "UPDATE books SET quantity = :newQuantity WHERE code = :code", nativeQuery = true)
    void updateBookQuantity(@Param("newQuantity") Integer newQuantity, @Param("code") Long code);
}
