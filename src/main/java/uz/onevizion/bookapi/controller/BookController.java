package uz.onevizion.bookapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.onevizion.bookapi.domain.AuthorBookDto;
import uz.onevizion.bookapi.domain.AuthorDto;
import uz.onevizion.bookapi.domain.BookDto;
import uz.onevizion.bookapi.service.BookService;

import java.util.List;


@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping("/all")
    public List<BookDto> getAll() {
        return service.getAllBooks();
    }

    @PostMapping("/create")
    public BookDto create(@RequestBody BookDto bookDto) {
        return service.create(bookDto);
    }

    @GetMapping("/author-books")
    public List<AuthorBookDto> getAuthorBooks() {
        return service.findAuthorBooks();
    }

    @GetMapping("/authors-by-title")
    public List<AuthorDto> getAuthorBooks(@RequestParam String symbol) {
        return service.getAuthorsBySymbol(symbol);
    }
}
