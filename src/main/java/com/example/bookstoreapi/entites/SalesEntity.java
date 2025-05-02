package com.example.bookstoreapi.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class SalesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_code")
    private Long bookCode;
    @Column(name = "id_customer")
    private Long customerId;

    public Long getId() {
        return id;
    }

    public Long getBookCode() {
        return bookCode;
    }

    public Long getCustomerId() {
        return customerId;
    }

}
