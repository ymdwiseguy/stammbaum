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

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = [FamilyTreeApplication])
@WebAppConfiguration
class FamilyTreeRepoCRUDSpec extends Specification {

    @Autowired
    private DataSource dataSource

    def JdbcTemplate jdbcTemplate

    def setup(){
        jdbcTemplate = new JdbcTemplate(dataSource)
    }

    def "connecting to repository works"(){
        given: "the repo and a person to create"
        FamilyTreeRepo familyTreeRepo = new FamilyTreeRepo(jdbcTemplate)
        Person person = new Person()

        when: "a new person is created"
        familyTreeRepo.createPerson(person)

        then: "no Exception is thrown"
        notThrown(Exception)

    }
}
