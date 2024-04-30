package uz.onevizion.bookapi.db.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import uz.onevizion.bookapi.db.entity.BookEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class BookMapper implements RowMapper<BookEntity> {

    @Override
    public BookEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookEntity.BookEntityBuilder builder = BookEntity.builder();

        return builder
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .author(rs.getString("author"))
                .description(rs.getString("description"))
                .build();
    }
}
