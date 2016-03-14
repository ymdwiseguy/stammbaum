package com.ymdwiseguy.views;

import com.github.jknack.handlebars.Handlebars;
import com.ymdwiseguy.PageTemplate;
import org.slf4j.Logger;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class PersonFormView {

    private static final Logger LOGGER = getLogger(PersonFormView.class);
    private final Handlebars handlebars;

    public PersonFormView(final Handlebars handlebars) {
        this.handlebars = handlebars;
    }

    public String render() {
        PageTemplate template;
        try {
            template = getIndexTemplate();
            return template.apply(null);
        } catch (IOException e) {
            LOGGER.error("could not load template file", e);
            return "Error";
        }
    }

    private PageTemplate getIndexTemplate() throws IOException {
        PageTemplate template;
        template = handlebars.compile("templates/editperson").as(PageTemplate.class);
        template.setPageTitle("Create new person");
        return template;
    }
}
