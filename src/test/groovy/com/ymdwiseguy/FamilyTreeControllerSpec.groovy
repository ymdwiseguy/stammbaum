package com.ymdwiseguy

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

@SpringApplicationConfiguration(classes = FamilyTreeApplication.class)
@WebAppConfiguration
class FamilyTreeControllerSpec extends Specification {

    String BASE_URL = "http://localhost"
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Value('${server.port}')
    protected int applicationPort = 8080

    def "expect bla"(){
//        mockMvc.perform(get("/index");
    }

    def "expect index Controller to return 'index'"(){

        given: "A request to index"
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(BASE_URL + ":" + applicationPort + "/index/", String.class);

        expect: "We make a GET request"
        responseEntity.body == "index"
    }
}
