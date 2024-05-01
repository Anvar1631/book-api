package uz.onevizion.bookapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.onevizion.bookapi.db.entity.BookEntity;
import uz.onevizion.bookapi.db.respository.BookRepository;
import uz.onevizion.bookapi.domain.AuthorBookDto;
import uz.onevizion.bookapi.domain.BookDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<AuthorBookDto> findAuthorBooks() {
        final List<BookEntity> bookEntities = bookRepository.findAll();
        final List<AuthorBookDto> books = new ArrayList<>();

        bookEntities
                .stream()
                .collect(Collectors.groupingBy(BookEntity::getAuthor, Collectors.mapping(BookService::convertToDto, Collectors.toList())))
                .forEach((author, bookList) -> {
                    AuthorBookDto authorBookDto = new AuthorBookDto(author, bookList);
                    books.add(authorBookDto);
                });

        return books;
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
