package com.example.bookstoreapi.entites.dtos.salesdtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InsertSaleDTO(
        @NotNull(message = "Código do livro não pode ser null")
        @NotBlank(message = "Código do livro deve ser preenchido")
        Long bookCode,
        @NotNull(message = "Id do cliente não pode ser null")
        @NotBlank(message = "Id do cliente deve ser preenchido")
        Long customerId
) {
}
