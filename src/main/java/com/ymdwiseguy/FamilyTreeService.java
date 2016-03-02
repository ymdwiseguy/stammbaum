package com.ymdwiseguy;

import com.github.jknack.handlebars.Handlebars;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class FamilyTreeService {

    private static final Logger LOGGER = getLogger(FamilyTreeService.class);

    private final FamilyTreeRepo familyTreeRepo;

    @Autowired
    public FamilyTreeService(final FamilyTreeRepo familyTreeRepo) {
        this.familyTreeRepo = familyTreeRepo;
    }

    public Optional<Person> getPerson(String uuid) {
        return familyTreeRepo.getPerson(uuid);
    }

    public String render(String personUUID) {
        Optional<Person> displayPerson = getPerson(personUUID);
        HashMap<String, Person> parents = getParents(personUUID);
        PersonTemplate template;
        try {
            template = getIndexTemplate();
            template.setParents(parents);
            if(displayPerson.isPresent()) {
                return template.apply(displayPerson.get());
            }
            return template.apply(null);
        } catch (IOException e) {
            LOGGER.error("could not load template file", e);
            return "Error";
        }
    }

    private PersonTemplate getIndexTemplate() throws IOException {
        PersonTemplate template;Handlebars handlebars = new Handlebars();
        template = handlebars.compile("templates/index").as(PersonTemplate.class);
        return template;
    }

    public HashMap<String, Person> getParents(String personUUID) {
        return familyTreeRepo.getListOfPersons(personUUID, "PARENT");
    }
}
