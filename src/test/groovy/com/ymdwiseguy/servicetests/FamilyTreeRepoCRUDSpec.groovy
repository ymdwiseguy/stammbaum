package com.ymdwiseguy.servicetests

import com.ymdwiseguy.FamilyTreeApplication
import com.ymdwiseguy.FamilyTreeRepo
import com.ymdwiseguy.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

import javax.sql.DataSource
import java.sql.Date

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = [FamilyTreeApplication])
@WebAppConfiguration
class FamilyTreeRepoCRUDSpec extends Specification {

    @Autowired
    private DataSource dataSource

    def JdbcTemplate jdbcTemplate
    Person person;
    UUID uuid;

    def setup(){
        jdbcTemplate = new JdbcTemplate(dataSource)
        uuid = UUID.randomUUID()
        Date birthdate = new Date(1980,7,11)
        person = new Person(uuid, "Matthias", "D.", birthdate as Date)
    }

    def "connecting to repository works"(){
        given: "the repo and a person to create"
        FamilyTreeRepo familyTreeRepo = new FamilyTreeRepo(jdbcTemplate)

        when: "a new person is created"
        UUID createdUuid = familyTreeRepo.createPerson(person)

        then: "no Exception is thrown"
        notThrown(Exception)

        and: "a uuid for the new user is returned"
        createdUuid != null

        when: "the person is read"
        Optional<Person> readPerson = familyTreeRepo.getPerson(createdUuid)

        then: "the expected person is returned"
        readPerson.get().getPersonUUID() == uuid
        readPerson.get().getFirstName() == "Matthias"
        readPerson.get().getLastName() == "D."
        readPerson.get().getBirthdate() == new Date(1980,7,11)
        when: "the data is updated"
        person.setFirstName("Matze")
        familyTreeRepo.updatePerson(person)

        and: "the data is read"
        Optional<Person> changedPerson = familyTreeRepo.getPerson(createdUuid)

        then: "the changed data is returned"
        changedPerson.get().getFirstName() == "Matze"

        when: "the data is deleted"
        familyTreeRepo.deletePerson(uuid)

        and: "the data is read"
        Optional<Person> deletedPerson = familyTreeRepo.getPerson(createdUuid)

        then: "the data was deleted"
        deletedPerson == Optional.empty()
    }

    def "fetching relatives works"(){
        given: "the repo and a person to fetch"
        FamilyTreeRepo familyTreeRepo = new FamilyTreeRepo(jdbcTemplate)
        UUID actualUUID = UUID.fromString('73c30299-e6c7-475f-a68b-61d6eb9b65a2')

        when: "the Person is fetched"
        Optional<Person> readPerson = familyTreeRepo.getPerson(actualUUID)

        then: "the person can be fetched from H2 db"
        readPerson.get().getFirstName() == "Matthias"

        when: "the relatives are fetched"
        List<Person> relatives = familyTreeRepo.getListOfPersons(actualUUID, 'PARENT')

        then: "two parent can be fetched from H2 db"
        relatives[0].getFirstName() == "Christa" || "Wolfgang"
        relatives.size() == 2

        when: "the relatives are fetched with a non existant relation"
        List<Person> nonrelatives = familyTreeRepo.getListOfPersons(actualUUID, 'blablabla')

        then: "no realtives are found"
        nonrelatives.size() == 0
    }
}
