package com.example.bookstoreapi.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authors")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
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
}
