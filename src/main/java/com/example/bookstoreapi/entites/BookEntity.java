package com.example.bookstoreapi.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
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

    public BookEntity(){}
}
