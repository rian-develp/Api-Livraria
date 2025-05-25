package com.example.bookstoreapi.entites.dtos.bookdtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateBookQuantityDTO(
        @NotNull(message = "Código não pode ser null")
        @NotBlank(message = "Código não pode estar vazio")
        @Pattern(regexp = "^[0-9]+$", message = "Código inválido")
        Long id,
        @NotNull(message = "Quantidade não pode ser null")
        @NotBlank(message = "Quantidade não pode estar vazio")
        @Pattern(regexp = "^[0-9]+$", message = "Quantidade inválido")
        Integer quantity
) {
}
