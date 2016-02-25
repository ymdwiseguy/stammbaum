package com.ymdwiseguy;

import org.slf4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

public class FamilyTreeRepo {

    private static final Logger LOGGER = getLogger(FamilyTreeRepo.class);
    private final JdbcTemplate jdbcTemplate;

    public FamilyTreeRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UUID createPerson(Person person) {
        final String sql = "INSERT INTO person (person_uuid, first_name, last_name, birthdate) VALUES (?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql, post -> {
                populatePersonStatement(person, post);
            });
        } catch (ConstraintViolationException cve) {
            throw cve;
        }
        LOGGER.info("Created person '{} {}'", person.getFirstName(), person.getLastName());
        return person.getPersonUUID();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Optional<Person> getPerson(UUID uuid) {
        final String sql = "SELECT * FROM person WHERE person_uuid=?";

        RowMapper<Person> personRowMapper = (resultSet, rowNum) -> new Person(
                UUID.fromString(resultSet.getString("person_uuid")),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getDate("birthdate")
        );
        try {
            LOGGER.info("Fetched person with uuid '{}'", uuid);
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, personRowMapper, uuid));
        } catch (EmptyResultDataAccessException e) {
            LOGGER.info("Person with id {} was not found in the database.", uuid);
            return Optional.empty();
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePerson(Person person) {
        final String sql = "UPDATE person SET person_uuid = ?, first_name = ?, last_name = ?, birthdate = ? WHERE person_uuid = ?";

        try {
            jdbcTemplate.update(sql, post -> {
                populatePersonStatement(person, post);
                post.setString(5, person.getPersonUUID().toString());
            });
            LOGGER.info("Updated person '{} {}'", person.getFirstName(), person.getLastName());
        } catch (ConstraintViolationException cve) {
            LOGGER.info("Person with id {} was not found in the database.", person.getPersonUUID().toString());
            throw cve;
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePerson(UUID uuid) {
        final String sql = "DELETE FROM person WHERE person_uuid = ?";
        jdbcTemplate.update(sql, uuid);
        LOGGER.info("Deleted client '{}' in the database", uuid);
    }

    private void populatePersonStatement(Person person, PreparedStatement post) throws SQLException {
        post.setString(1, person.getPersonUUID().toString());
        post.setString(2, person.getFirstName());
        post.setString(3, person.getLastName());
        post.setDate(  4, person.getBirthdate());
    }



    @Transactional(readOnly = true)
    public List<Person> getListOfPersons(UUID personUUID, String relation) {
        final String sql = "SELECT * FROM PERSON WHERE PERSON_UUID IN (SELECT RELATIVE FROM RELATIONS WHERE PERSON_UUID = ? AND RELATION_TYPE = ?)";
        List<Person> relatives = jdbcTemplate.query(sql, populateRelationStatement(personUUID, relation), personRowMapper);

        LOGGER.info("Found "+relatives.size() + " relatives");
        return relatives;
    }

    private PreparedStatementSetter populateRelationStatement(UUID personUUID, String relation) {
        return (PreparedStatement ps) -> {
            ps.setString(1, personUUID.toString());
            ps.setString(2, relation);
        };
    }

    private final RowMapper<Person> personRowMapper = (ResultSet rs, int rowNum) -> {
        Person person = new Person();
        person.setPersonUUID(UUID.fromString(rs.getString("person_uuid")));
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        person.setBirthdate(rs.getDate("birthdate"));
        return person;
    };
}
