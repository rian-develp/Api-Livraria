package com.example.bookstoreapi.repositories;

import com.example.bookstoreapi.entites.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalesRepository extends JpaRepository<SalesEntity, Long> {

    @Query("SELECT s FROM SalesEntity s " +
            "JOIN FETCH s.book " +
            "JOIN FETCH s.customer"
    )
    List<SalesEntity> getAllSalesDetails();

    @Query("SELECT s FROM SalesEntity s " +
            "JOIN FETCH s.book b " +
            "JOIN FETCH s.customer c " +
            "WHERE c.id =: customerId"
    )
    List<SalesEntity> getAllSalesDetailsByCustomerId(@Param("customerId") Long customerId);

}
