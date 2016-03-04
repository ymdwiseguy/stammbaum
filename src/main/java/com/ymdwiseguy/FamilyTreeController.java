package com.ymdwiseguy;

import com.ymdwiseguy.views.FamilyTreeView;
import com.ymdwiseguy.views.ListPersonsView;
import com.ymdwiseguy.views.NewPersonGetView;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class FamilyTreeController {

    private static final Logger LOGGER = getLogger(FamilyTreeController.class);

    @Autowired
    FamilyTreeService familyTreeService;

    @Autowired
    FamilyTreeView familyTreeView;

    @Autowired
    ListPersonsView listPersonsView;

    @Autowired
    NewPersonGetView newPersonGetView;

    @RequestMapping(value = "/")
    public String index(@RequestParam(value = "uuid", required = false, defaultValue = "73c30299-e6c7-475f-a68b-61d6eb9b65a2") String uuid) {
        LOGGER.info("Render family tree for person with uuid " + uuid);
        Optional<Person> displayPerson = familyTreeService.getPerson(uuid);
        HashMap<String, Person> parents = familyTreeService.getParents(uuid);
        return familyTreeView.render(displayPerson, parents);
    }

    @RequestMapping(value = "/list")
    public String list() {
        LOGGER.info("Render list of all persons");
        HashMap<String, Person> persons = familyTreeService.getPersons();
        return listPersonsView.render(persons);
    }

    @RequestMapping(value = "/person", method = GET)
    public String form() {
        LOGGER.info("Render form for new person");
        return newPersonGetView.render();
    }

    @RequestMapping(value = "/person", method = POST)
    public String postForm() {
        LOGGER.info("Handle post for new person");
        return newPersonGetView.render();
    }

    @RequestMapping(value = "/person", method = PUT)
    public String putForm() {
        LOGGER.info("Handle edit for person");
        return newPersonGetView.render();
    }
}
