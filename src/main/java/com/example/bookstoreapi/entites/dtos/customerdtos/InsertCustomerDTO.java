package com.example.bookstoreapi.entites.dtos.customerdtos;

public record InsertCustomerDTO(
        String cpf,
        String email,
        String name
) {
}
