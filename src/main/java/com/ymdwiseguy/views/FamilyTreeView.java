package com.ymdwiseguy.views;

import com.github.jknack.handlebars.Handlebars;
import com.ymdwiseguy.Person;
import com.ymdwiseguy.PageTemplate;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class FamilyTreeView {

    private static final Logger LOGGER = getLogger(FamilyTreeView.class);
    private final Handlebars handlebars;

    public FamilyTreeView(final Handlebars handlebars) {
        this.handlebars = handlebars;
    }

    public String render(Optional<Person> displayPerson, HashMap<String, Person> parents) {

        PageTemplate template;
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


    private PageTemplate getIndexTemplate() throws IOException {
        PageTemplate template;
        template = handlebars.compile("templates/index").as(PageTemplate.class);
        template.setPageTitle("Home");
        return template;
    }
}
