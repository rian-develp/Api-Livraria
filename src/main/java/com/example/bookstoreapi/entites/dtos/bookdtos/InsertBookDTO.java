package com.example.bookstoreapi.entites.dtos.bookdtos;

public record InsertBookDTO(
    String authorName,
    Double price,
    String publishDate,
    Integer quantity,
    String title
){
}
