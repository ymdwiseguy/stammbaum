package com.ymdwiseguy

import org.springframework.jdbc.core.JdbcTemplate
import spock.lang.Specification

import java.sql.Date

class FamilyTreeRepoSpec extends Specification {

    def JdbcTemplate jdbcTemplate
    Person person;
    UUID uuid;

    def setup(){
        jdbcTemplate = Mock(JdbcTemplate)
        uuid = UUID.randomUUID()
        Date birthdate = new Date(1980,7,11)
        person = new Person(uuid, "Matthias", "D.", birthdate as Date)
    }

    def "connecting to repository works"(){
        given: "the repo and a person to create"
        FamilyTreeRepo familyTreeRepo = new FamilyTreeRepo(jdbcTemplate)

        when: "a new person is created"
        familyTreeRepo.createPerson(person)

        then: "no Exception is thrown"
        notThrown(Exception)
    }

    def "fetching a non existent person returns an empty optional"(){
        given: "the repo"
        jdbcTemplate.query(*_) >> null
        FamilyTreeRepo familyTreeRepo = new FamilyTreeRepo(jdbcTemplate)

        when: "a non existing person is read"
        Optional<Person> getPerson = familyTreeRepo.getPerson(uuid)

        then: "a NoSuchElementExeption is thrown"
        getPerson == Optional.empty()
    }

    def "fetching a exiting person returns a person"(){
        given: "the repo returning a Person"
        jdbcTemplate.queryForObject(*_) >> person
        FamilyTreeRepo familyTreeRepo = new FamilyTreeRepo(jdbcTemplate)

        when: "the person is read"
        Optional<Person> getPerson = familyTreeRepo.getPerson(uuid)

        then: "the expected person is returned"
        getPerson.get().getPersonUUID() == uuid
    }

}
