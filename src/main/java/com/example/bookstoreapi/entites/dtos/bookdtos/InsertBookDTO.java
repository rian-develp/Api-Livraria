package com.example.bookstoreapi.entites.dtos.bookdtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InsertBookDTO(
    @NotNull(message = "Nome do autor não pode ser null")
    @NotBlank(message = "Nome do autor deve ser preenchido")
    String authorName,
    @NotNull(message = "Preço não pode ser null")
    @NotBlank(message = "Preço deve ser preenchido")
    Double price,
    @NotNull(message = "Data não pode ser null")
    @NotBlank(message = "Data deve ser preenchida")
    String publishDate,
    @NotNull(message = "Preço não pode ser null")
    @NotBlank(message = "Preço deve ser preenchido")
    Integer quantity,
    @NotNull(message = "Título não pode ser null")
    @NotBlank(message = "Título deve ser preenchido")
    String title
){
}
