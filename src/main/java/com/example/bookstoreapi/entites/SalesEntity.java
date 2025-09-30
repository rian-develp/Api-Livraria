package com.example.bookstoreapi.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class SalesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sale_hour")
    private LocalDateTime saleHour;
    @ManyToOne
    @JoinColumn(name = "book_code")
    @NonNull
    private BookEntity book;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    @NonNull
    private CustomerEntity customer;
}