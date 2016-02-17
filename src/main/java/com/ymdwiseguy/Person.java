package com.ymdwiseguy;

import java.sql.Date;

public class Person {
    private String personUUID;
    private String firstName;
    private String lastName;
    private Date birthdate;

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
}
