package com.ymdwiseguy;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class FamilyTreeController {

    private static final Logger LOGGER = getLogger(FamilyTreeController.class);

    @Autowired
    FamilyTreeService familyTreeService;

    @Value("${handlebars.prefix}")
    String templatePrefix;

    @RequestMapping(value = "/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix(templatePrefix);
        Handlebars handlebars = new Handlebars(loader);

        try {
            LOGGER.info("rendering template");

            Template template = handlebars.compile("index");
            return template.apply(name);
        } catch (IOException e) {
            LOGGER.error("could not load template file", e);
        }

        return null;
    }
}
