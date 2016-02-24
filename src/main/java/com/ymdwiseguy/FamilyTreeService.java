package com.ymdwiseguy;

import com.github.jknack.handlebars.Handlebars;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class FamilyTreeService {

    private static final Logger LOGGER = getLogger(FamilyTreeService.class);

    private final FamilyTreeRepo familyTreeRepo;

    @Autowired
    public FamilyTreeService(final FamilyTreeRepo familyTreeRepo) {
        this.familyTreeRepo = familyTreeRepo;
    }

    public Optional<Person> getPerson(UUID uuid) {
        return familyTreeRepo.getPerson(uuid);
    }

    public String render(Handlebars handlebars, Optional<Person> displayPerson) {
        PersonTemplate template;
        try {
            template = handlebars.compile("templates/index").as(PersonTemplate.class);
            return template.apply(displayPerson.get());
        } catch (IOException e) {
            LOGGER.error("could not load template file", e);
            return "Error";
        }
    }
}
