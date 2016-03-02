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
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class FamilyTreeRepo {

    private static final Logger LOGGER = getLogger(FamilyTreeRepo.class);
    private final JdbcTemplate jdbcTemplate;

    public FamilyTreeRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String createPerson(Person person) {
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
    public Optional<Person> getPerson(String uuid) {
        final String sql = "SELECT * FROM person WHERE person_uuid=?";

        RowMapper<Person> personRowMapper = (resultSet, rowNum) -> new Person(
                resultSet.getString("person_uuid"),
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
                post.setString(5, person.getPersonUUID());
            });
            LOGGER.info("Updated person '{} {}'", person.getFirstName(), person.getLastName());
        } catch (ConstraintViolationException cve) {
            LOGGER.info("Person with id {} was not found in the database.", person.getPersonUUID());
            throw cve;
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePerson(String uuid) {
        final String sql = "DELETE FROM person WHERE person_uuid = ?";
        jdbcTemplate.update(sql, uuid);
        LOGGER.info("Deleted client '{}' in the database", uuid);
    }

    private void populatePersonStatement(Person person, PreparedStatement post) throws SQLException {
        post.setString(1, person.getPersonUUID());
        post.setString(2, person.getFirstName());
        post.setString(3, person.getLastName());
        post.setDate(4, person.getBirthdate());
    }


    @Transactional(readOnly = true)
    public HashMap<String, Person> getListOfPersons(String personUUID, String relation) {
        final String sql = "SELECT * FROM person WHERE person_uuid IN (SELECT relative FROM relations WHERE person_uuid = ? AND relation_type = ?)";
        List<Person> relatives = jdbcTemplate.query(sql, populateRelationStatement(personUUID, relation), personRowMapper);
        HashMap<String, Person> relativesMap = new HashMap<>();
        for (Person relative : relatives) {
            String uuid = relative.getPersonUUID();
            if (!relativesMap.containsKey(uuid)) {
                relativesMap.put(uuid, relative);
            }
        }
        LOGGER.info("Found " + relatives.size() + " relatives");
        return relativesMap;
    }

    private PreparedStatementSetter populateRelationStatement(String personUUID, String relation) {
        return (PreparedStatement ps) -> {
            ps.setString(1, personUUID);
            ps.setString(2, relation);
        };
    }

    private final RowMapper<Person> personRowMapper = (ResultSet rs, int rowNum) -> {
        Person person = new Person();
        person.setPersonUUID(rs.getString("person_uuid"));
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        person.setBirthdate(rs.getDate("birthdate"));
        return person;
    };
}
