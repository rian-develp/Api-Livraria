package com.example.bookstoreapi.entites.dtos.customerdtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InsertCustomerDTO(
        @NotNull(message = "CPF não pode ser null")
        @NotBlank(message = "CPF deve preenchido")
        String cpf,
        @NotNull(message = "Email não pode ser null")
        @NotBlank(message = "Email deve preenchido")
        String email,
        @NotNull(message = "Nome do cliente não pode ser null")
        @NotBlank(message = "Nome do cliente deve preenchido")
        String name
) {
}
