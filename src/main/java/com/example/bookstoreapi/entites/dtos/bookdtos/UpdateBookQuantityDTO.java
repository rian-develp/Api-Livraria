package com.example.bookstoreapi.entites.dtos.bookdtos;

public record UpdateBookQuantityDTO(
        Long id,
        Integer quantity
) {
}
