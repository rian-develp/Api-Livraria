package com.example.bookstoreapi.services;

import com.example.bookstoreapi.entites.BookEntity;
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
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<BookEntity> getAllBooks(){
        return repository.findAll();
    }

    public Optional<?> getBookByCode(Long code) throws Exception{

        if (code <= 0)
            throw new NotAllowedValueException("Valor não permitido");

        return repository.findById(code);
    }

    public void insertBook(
        String authorName, Double price,
        String publishDate, Integer quantity,
        String title
    ) throws Exception {

//        if( authorName == null || authorName.isBlank() ||
//            title == null || title.isBlank() ||
//            price == null || publishDate == null ||
//            quantity == null
//        ){
//            throw new EmptyFieldsException("Preencha todos os campos corretamente");
//        }

        if (price < 0 || quantity <= 0)
            throw new NotAllowedValueException("Insira um valor válido");

//        if (publishDate == null || publishDate.isBlank())
//            throw new NotAllowedValueException("Insira uma data válida");

        var entityList = getAllBooks();
        for (BookEntity b : entityList){
            if (b.getTitle().equalsIgnoreCase(title) && b.getAuthorName().equalsIgnoreCase(authorName)){
                throw new EntityExistsException("Livro já existe");
            }
        }

        var dateConverted = validDate(publishDate);
        BookEntity entity = new BookEntity(authorName, price, dateConverted, quantity, title);
        repository.save(entity);
    }

    public void updateBookPrice(Double price, Long code) throws Exception{

        if ((code == null || code <= 0) || (price == null || price <= 0))
            throw new NotAllowedValueException("Valores não permitidos");

        var entity = getBookByCode(code);

        if (entity.isEmpty())
            throw new EntityNotFoundException("Livro não existe");

        repository.updateBookPrice(price, code);
    }

    public boolean updateBookQuantity(Integer quantity, Long code) throws Exception {
        if ((code == null || code <= 0) || (quantity == null || quantity <= 0)){
            throw new NotAllowedValueException("Valores não permitidos");
        }

        var entity = getBookByCode(code);

        if (entity.isPresent()) {
            repository.updateBookQuantity(quantity, code);
            return true;
        }

        return false;
    }

    public LocalDate validDate(String date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
