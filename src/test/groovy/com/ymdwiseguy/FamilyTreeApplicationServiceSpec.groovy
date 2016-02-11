package com.ymdwiseguy

import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

@SpringApplicationConfiguration(classes = FamilyTreeApplication.class)
@WebAppConfiguration
class FamilyTreeApplicationServiceSpec extends Specification {

    def "application context loads"() {
        expect: "load application context"
    }
}
