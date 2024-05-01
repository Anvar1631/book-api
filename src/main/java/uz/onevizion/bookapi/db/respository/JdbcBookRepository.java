package uz.onevizion.bookapi.db.respository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import uz.onevizion.bookapi.db.entity.BookEntity;
import uz.onevizion.bookapi.db.rowmapper.BookMapper;
import uz.onevizion.bookapi.domain.BookDto;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class JdbcBookRepository implements BookRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final BookMapper bookMapper;
    private final AtomicLong idGenerator;

    public JdbcBookRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                              JdbcTemplate jdbcTemplate,
                              BookMapper bookMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.bookMapper = bookMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("BOOK");
        this.idGenerator = new AtomicLong(6);
    }

    @Override
    public List<BookEntity> findAll() {
        final String query = "SELECT * FROM BOOK";
        return namedParameterJdbcTemplate.query(query, bookMapper);
    }

    @Override
    public BookDto createBook(BookDto book) {
        book.setId(idGenerator.getAndIncrement());

        simpleJdbcInsert.execute(book.toMap());

        return book;
    }
}
