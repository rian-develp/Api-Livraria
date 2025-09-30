package com.example.bookstoreapi.services;

import com.example.bookstoreapi.entites.BookEntity;
import com.example.bookstoreapi.entites.dtos.bookdtos.InsertBookDTO;
import com.example.bookstoreapi.entites.dtos.bookdtos.UpdateBookPriceDTO;
import com.example.bookstoreapi.exceptions.EmptyFieldsException;
import com.example.bookstoreapi.exceptions.NotAllowedValueException;
import com.example.bookstoreapi.repositories.BookRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<BookEntity> getAllBooks(){
        return repository.findAll();
    }

    public BookEntity getBookByCode(Long code) throws Exception{

        if (code <= 0)
            throw new NotAllowedValueException("Valor não permitido");

        return repository.findById(code).orElseThrow(() -> new EntityNotFoundException(""));
    }

    public void insertBook(InsertBookDTO dto) throws Exception {

        if(dto.authorName().isBlank() || dto.title().isBlank() ||
            dto.publishDate().isBlank() || dto.price() == null ||
                dto.quantity() == null
        ){
            throw new EmptyFieldsException("Preencha todos os campos corretamente");
        }

        if (dto.price() < 0 || dto.quantity() <= 0)
            throw new NotAllowedValueException("Insira um valor válido");

        var entityList = getAllBooks();
        for (BookEntity b : entityList){
            if (b.getTitle().equalsIgnoreCase(dto.title()) && b.getAuthorName().equalsIgnoreCase(dto.authorName())){
                throw new EntityExistsException("Livro já existe");
            }
        }

        var dateConverted = validDate(dto.publishDate());
        BookEntity entity = new BookEntity(dto.authorName(), dto.price(), dateConverted, dto.quantity(), dto.title());
        repository.save(entity);
    }

    public void updateBookPrice(UpdateBookPriceDTO dto) throws Exception{

        if ((dto.code() == null || dto.code() <= 0) || (dto.price() == null || dto.price() <= 0))
            throw new NotAllowedValueException("Valores não permitidos");

        var entity = getBookByCode(dto.code());

        if (entity == null)
            throw new EntityNotFoundException("Livro não existe");

        repository.updateBookPrice(dto.price(), dto.code());
    }

    public void updateBookQuantity(Integer quantity, Long code) throws Exception {
        if ((code == null || code <= 0) || (quantity == null || quantity <= 0)){
            throw new NotAllowedValueException("Valores não permitidos");
        }

        var entity = getBookByCode(code);
        if (entity == null)
            throw new EntityNotFoundException("Livro não existe");

        repository.updateBookQuantity(quantity, code);
    }

    private LocalDate validDate(String date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
