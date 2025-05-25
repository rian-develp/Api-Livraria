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
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    @Column(nullable = false, length = 30)
    @NonNull
    private String citizen;
    @Column(nullable = false, length = 46)
    @NonNull
    private String name;

    public AuthorEntity(){}
}
