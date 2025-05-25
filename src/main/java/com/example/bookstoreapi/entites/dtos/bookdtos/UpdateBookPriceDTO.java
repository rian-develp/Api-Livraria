package com.example.bookstoreapi.entites.dtos.bookdtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateBookPriceDTO(
        @NotNull(message = "Código não pode ser null")
        @NotBlank(message = "Código não pode estar vazio")
        @Pattern(regexp = "^[0-9]+$", message = "Código inválido")
        Long id,
        @NotNull(message = "Preço não pode ser null")
        @NotBlank(message = "Preço não pode estar vazio")
        @Pattern(regexp = "\\d+(.\\d{1,2})?$", message = "Preço inválido")
        Double price
) {
}
