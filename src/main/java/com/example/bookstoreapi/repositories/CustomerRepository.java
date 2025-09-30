package com.example.bookstoreapi.repositories;

import com.example.bookstoreapi.entites.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.email = :email")
    Optional<CustomerEntity> getCustomerByEmail(@Param("email") String email);

    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.cpf = :cpf")
    Optional<CustomerEntity> getCustomerByCpf(String cpf);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM CustomerEntity c WHERE c.email = :email")
    void deleteUserByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM CustomerEntity c WHERE c.email = :email")
    void deleteUserByCpf(String cpf);

}
