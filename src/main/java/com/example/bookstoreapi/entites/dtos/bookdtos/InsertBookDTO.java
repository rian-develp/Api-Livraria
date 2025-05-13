package com.example.bookstoreapi.entites.dtos.bookdtos;


import java.sql.Date;

public record InsertBookDTO(
    String authorName,
    Double price,
    Date publishDate,
    Integer quantity,
    String title
){
}
