package uz.onevizion.bookapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.onevizion.bookapi.db.entity.BookEntity;
import uz.onevizion.bookapi.domain.BookDto;
import uz.onevizion.bookapi.db.respository.BookRepository;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDto> getAllBooks() {

        List<BookEntity> bookEntities = bookRepository.findAll();

        return bookEntities
                .stream()
                .map(BookService::convertToDto)
                .toList();
    }

    public BookDto create(BookDto bookDto) {
        return bookRepository.createBook(bookDto);
    }

    private static BookDto convertToDto(BookEntity m) {
        return BookDto.builder()
                .id(m.getId())
                .author(m.getAuthor())
                .title(m.getTitle())
                .description(m.getDescription())
                .build();
    }
}
