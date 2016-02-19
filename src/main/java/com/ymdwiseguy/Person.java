package com.ymdwiseguy;

import java.sql.Date;
import java.util.UUID;

public class Person {
    private UUID personUUID;
    private String firstName;
    private String lastName;
    private Date birthdate;

    public Person(UUID person_uuid, String first_name, String last_name, Date birthdate) {
        setPersonUUID(person_uuid);
        setFirstName(first_name);
        setLastName(last_name);
        setBirthdate(birthdate);
    }

    public UUID getPersonUUID() {
        return personUUID;
    }

    public void setPersonUUID(UUID personUUID) {
        this.personUUID = personUUID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public UUID createUUID() {
        UUID uuid = UUID.randomUUID();
        this.setPersonUUID(uuid);
        return uuid;
    }
}
