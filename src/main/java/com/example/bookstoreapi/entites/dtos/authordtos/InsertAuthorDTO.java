package com.example.bookstoreapi.entites.dtos.authordtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InsertAuthorDTO(
        @NotNull(message = "Cidadania não ser null")
        @NotBlank(message = "Cidadania deve ser preenchida")
        String citizen,
        @NotNull(message = "Nome não ser null")
        @NotBlank(message = "Nome deve ser preenchida")
        String name
) {
}
