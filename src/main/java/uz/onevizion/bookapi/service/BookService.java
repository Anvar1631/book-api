package uz.onevizion.bookapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.onevizion.bookapi.db.entity.BookEntity;
import uz.onevizion.bookapi.db.respository.BookRepository;
import uz.onevizion.bookapi.domain.AuthorBookDto;
import uz.onevizion.bookapi.domain.AuthorDto;
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
                .forEach((author, bookList) -> books.add(new AuthorBookDto(author, bookList)));

        return books;
    }

    public List<AuthorDto> getAuthorsBySymbol(String symbol) {
        List<BookEntity> books = bookRepository.findAll();

        return books
                .stream()
                .map(book -> getAuthorDto(symbol, book))
                .filter(f -> f.getBookCount() > 0)
                .collect(Collectors.groupingBy(AuthorDto::getName, Collectors.summingInt(AuthorDto::getBookCount)))
                .entrySet()
                .stream()
                .map((each) -> new AuthorDto(each.getKey(), each.getValue()))
                .sorted((e1, e2) -> e2.getBookCount().compareTo(e1.getBookCount()))
                .limit(10)
                .collect(Collectors.toList());
    }

    private static AuthorDto getAuthorDto(String symbol, BookEntity book) {
        if (!book.getTitle().contains(symbol)) {
            return new AuthorDto(book.getAuthor(), 0);
        }

        int count = (int) book.getTitle()
                .chars()
                .filter(ch -> Character
                        .toString(ch)
                        .equalsIgnoreCase(symbol))
                .count();

        return new AuthorDto(book.getAuthor(), count);
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
