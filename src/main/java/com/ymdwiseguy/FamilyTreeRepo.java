package com.ymdwiseguy;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.validation.ConstraintViolationException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FamilyTreeRepo {

    private final JdbcTemplate jdbcTemplate;

    public FamilyTreeRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createPerson(Person person) {
        final String sql = "INSERT INTO person (person_uuiid, first_name, last_name, birthdate) VALUES (?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql, post -> {
                populateStatement(person, post);
            });
        } catch (ConstraintViolationException cve) {
            throw cve;
        }
    }


    private void populateStatement(Person person, PreparedStatement post) throws SQLException {
        post.setString(1, person.getPersonUUID());
        post.setString(2, person.getFirstName());
        post.setString(3, person.getLastName());
        post.setDate(  4, person.getBirthdate());
    }
}
