package com.ymdwiseguy

import org.springframework.jdbc.core.JdbcTemplate
import spock.lang.Specification

class FamilyTreeRepoSpec extends Specification {

    def JdbcTemplate jdbcTemplate

    def setup(){
        jdbcTemplate = Mock(JdbcTemplate)
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
