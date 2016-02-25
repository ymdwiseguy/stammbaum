package com.ymdwiseguy;

import com.github.jknack.handlebars.Handlebars;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class FamilyTreeController {

    private static final Logger LOGGER = getLogger(FamilyTreeController.class);

    @Autowired
    FamilyTreeService familyTreeService;

    @RequestMapping(value = "/greeting")
    public String greeting(@RequestParam(value = "uuid", required = false, defaultValue = "World") String uuid) {
        Handlebars handlebars = new Handlebars();
        UUID personUUID = UUID.fromString(uuid);
        Optional<Person> displayPerson = familyTreeService.getPerson(personUUID);
        ArrayList<Person> parents = familyTreeService.getParents(personUUID);
        return familyTreeService.render(handlebars, displayPerson);
    }
}
