package it.training.spring.dayone.jdbc;

import it.training.spring.dayone.model.People;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleMapper implements RowMapper<People> {

    @Override
    public People mapRow(ResultSet rs, int rowNum) throws SQLException {
        People p = new People();
        p.setId(rs.getLong("id"));
        p.setFirstName(rs.getString("firstName"));
        p.setLastName(rs.getString("lastName"));
        p.setEmail(rs.getString("email"));

        return p;
    }
}
