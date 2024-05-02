package uz.onevizion.bookapi.domain;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private String name;
    private Integer bookCount;
}
