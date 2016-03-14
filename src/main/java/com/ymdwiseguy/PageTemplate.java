package com.ymdwiseguy;

import com.github.jknack.handlebars.TypeSafeTemplate;

import java.sql.Date;
import java.util.HashMap;
import java.util.UUID;

public interface PageTemplate extends TypeSafeTemplate<Person> {
    public PageTemplate setPersonUUID(UUID personUUID);
    public PageTemplate setFirstName(String firstName);
    public PageTemplate setLastName(String lastName);
    public PageTemplate setBirthdate(Date birthdate);
    public PageTemplate setAdditionalInformation(String additionalInformation);

    public PageTemplate setParents(HashMap<String, Person> parents);
    public PageTemplate setPageTitle(String pageTitle);
}