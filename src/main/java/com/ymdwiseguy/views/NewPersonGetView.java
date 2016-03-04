package com.ymdwiseguy.views;

import com.github.jknack.handlebars.Handlebars;
import com.ymdwiseguy.PersonTemplate;
import org.slf4j.Logger;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class NewPersonGetView {

    private static final Logger LOGGER = getLogger(NewPersonGetView.class);
    private final Handlebars handlebars;

    public NewPersonGetView(final Handlebars handlebars) {
        this.handlebars = handlebars;
    }

    public String render() {
        PersonTemplate template;
        try {
            template = getIndexTemplate();
            return template.apply(null);
        } catch (IOException e) {
            LOGGER.error("could not load template file", e);
            return "Error";
        }
    }

    private PersonTemplate getIndexTemplate() throws IOException {
        PersonTemplate template;
        template = handlebars.compile("templates/editperson").as(PersonTemplate.class);
        return template;
    }
}
