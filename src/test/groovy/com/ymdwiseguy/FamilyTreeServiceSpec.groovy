package com.ymdwiseguy

import spock.lang.Ignore
import spock.lang.Specification

class FamilyTreeServiceSpec extends Specification {

    @Ignore
    def "expect to render some html"(){
        FamilyTreeRepo familyTreeRepo = Mock(FamilyTreeRepo)
        String uuid = UUID.randomUUID().toString()
        given: "the service"
        FamilyTreeService familyTreeService = new FamilyTreeService(familyTreeRepo)

        when: "the render method is called"
        String result = familyTreeService.render(uuid)

        then: "it returns a string"
        result.contains("<html>")
    }
}
