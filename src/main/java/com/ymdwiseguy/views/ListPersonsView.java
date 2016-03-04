package com.ymdwiseguy.views;

import com.github.jknack.handlebars.Handlebars;
import com.ymdwiseguy.Person;
import com.ymdwiseguy.PersonTemplate;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.HashMap;

import static org.slf4j.LoggerFactory.getLogger;

public class ListPersonsView {

    private static final Logger LOGGER = getLogger(ListPersonsView.class);
    private final Handlebars handlebars;

    public ListPersonsView(final Handlebars handlebars) {
        this.handlebars = handlebars;
    }

    public String render(HashMap<String, Person> persons) {

        PersonTemplate template;
        try {
            template = getIndexTemplate();
            template.setParents(persons);
            return template.apply(null);
        } catch (IOException e) {
            LOGGER.error("could not load template file", e);
            return "Error";
        }
    }


    private PersonTemplate getIndexTemplate() throws IOException {
        PersonTemplate template;
        template = handlebars.compile("templates/listview").as(PersonTemplate.class);
        return template;
    }
}
