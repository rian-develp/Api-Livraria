package com.example.bookstoreapi.entites;

import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    @Column(name = "author_name", nullable = false, length = 24)
    private String authorName;
    @Column(nullable = false)
    private Double price;
    @Column(name = "publish_date", nullable = false)
    private Date publishDate;
    @Column(nullable = false)
    private Integer quantity;
    @Column(length = 42, nullable = false)
    private String title;
}
