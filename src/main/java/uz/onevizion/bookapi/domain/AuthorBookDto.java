package uz.onevizion.bookapi.domain;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorBookDto {
    private String name;
    private List<BookDto> books;
}
