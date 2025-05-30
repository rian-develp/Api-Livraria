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
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 14, nullable = false, unique = true)
    @NonNull
    private String cpf;
    @Column(length = 28, nullable = false, unique = true)
    @NonNull
    private String email;
    @Column(length = 26, nullable = false)
    @NonNull
    private String name;

    public CustomerEntity(){}
}
