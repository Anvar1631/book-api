package uz.onevizion.bookapi.db.respository;


import uz.onevizion.bookapi.db.entity.BookEntity;
import uz.onevizion.bookapi.domain.BookDto;

import java.util.List;

public interface BookRepository {

    List<BookEntity> findAll();

    BookDto createBook(BookDto book);
}
