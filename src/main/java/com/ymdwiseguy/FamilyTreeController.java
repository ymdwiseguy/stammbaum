package com.ymdwiseguy;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class FamilyTreeController {

    private static final Logger LOGGER = getLogger(FamilyTreeController.class);

    @Autowired
    FamilyTreeService familyTreeService;

    @Autowired
    FamilyTreeView familyTreeView;

    @RequestMapping(value = "/index")
    public String greeting(@RequestParam(value = "uuid", required = false, defaultValue = "73c30299-e6c7-475f-a68b-61d6eb9b65a2") String uuid) {
        LOGGER.info("Render family tree for person with uuid " + uuid);
        Optional<Person> displayPerson = familyTreeService.getPerson(uuid);
        HashMap<String, Person> parents = familyTreeService.getParents(uuid);
        return familyTreeView.render(displayPerson, parents);
    }
}
