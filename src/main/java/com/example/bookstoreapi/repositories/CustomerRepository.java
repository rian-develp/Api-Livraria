package com.example.bookstoreapi.repositories;

import com.example.bookstoreapi.entites.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query(value = "SELECT * FROM customers WHERE email = :email", nativeQuery = true)
    CustomerEntity getCustomerByEmail(@Param("email") String email);

}
