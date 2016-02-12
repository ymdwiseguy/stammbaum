package com.ymdwiseguy

import spock.lang.Specification

class FamilyTreeServiceSpec extends Specification {

    def "expect to render some html"(){
        given: "the service"
        FamilyTreeService familyTreeService = new FamilyTreeService();

        when: "the render method is called"
        String result = familyTreeService.render()

        then: "it returns a string"
        result.contains("<html>")
    }
}
