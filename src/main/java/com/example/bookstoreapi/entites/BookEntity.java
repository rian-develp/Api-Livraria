package com.example.bookstoreapi.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    @Column(name = "author_name", nullable = false, length = 24)
    @NonNull
    private String authorName;
    @Column(nullable = false)
    @NonNull
    private Double price;
    @Column(name = "publish_date", nullable = false)
    @NonNull
    private LocalDate publishDate;
    @Column(nullable = false)
    @NonNull
    private Integer quantity;
    @Column(length = 42, nullable = false)
    @NonNull
    private String title;
}
