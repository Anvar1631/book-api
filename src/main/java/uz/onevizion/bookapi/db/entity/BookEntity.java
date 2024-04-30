package uz.onevizion.bookapi.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookEntity {

    private Long id;
    private String title;
    private String author;
    private String description;
}
