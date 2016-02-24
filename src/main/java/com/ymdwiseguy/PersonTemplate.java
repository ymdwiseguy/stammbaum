package com.ymdwiseguy;

import com.github.jknack.handlebars.TypeSafeTemplate;

import java.sql.Date;
import java.util.UUID;

public interface PersonTemplate extends TypeSafeTemplate<Person> {
    public PersonTemplate setPersonUUID(UUID personUUID);
    public PersonTemplate setFirstName(String firstName);
    public PersonTemplate setLastName(String lastName);
    public PersonTemplate setBirthdate(Date birthdate);
}