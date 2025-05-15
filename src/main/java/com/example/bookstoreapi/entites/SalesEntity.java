package com.example.bookstoreapi.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sales")
public class SalesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_code")
    @NonNull
    private Long bookCode;
    @Column(name = "id_customer")
    @NonNull
    private Long customerId;
}
