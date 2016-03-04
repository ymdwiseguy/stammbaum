package com.ymdwiseguy;

import java.sql.Date;
import java.util.UUID;

public class Person {
    private String personUUID;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String additionalInformation;

    public Person(String person_uuid, String first_name, String last_name, Date birthdate) {
        setPersonUUID(person_uuid);
        setFirstName(first_name);
        setLastName(last_name);
        setBirthdate(birthdate);
    }

    public Person() {

    }

    public String getPersonUUID() {
        return personUUID;
    }

    public void setPersonUUID(String personUUID) {
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

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String createUUID() {
        String uuid = UUID.randomUUID().toString();
        this.setPersonUUID(uuid);
        return uuid;
    }
}
