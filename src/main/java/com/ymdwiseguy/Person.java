package com.ymdwiseguy;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.UUID;

public class Person {

    protected static final String MESSAGE_KEY_PREFIX = "value of field ";
    protected static final String MESSAGE_MULTI_KEY_PREFIX = "at least one value of field ";
    protected static final String MESSAGE_KEY_SUFFIX_INVALID = " is invalid.";
    protected static final String MESSAGE_KEY_SUFFIX_GREATER_THAN_0 = " must be greater than 0";
    protected static final String MESSAGE_VALUE_LENGTH_EXCEEDED_PREFIX = "length of field ";
    protected static final String MESSAGE_VALUE_LENGTH_EXCEEDED_SUFFIX = " exceeded. Maximum is ";

    private String personUUID;

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = MESSAGE_VALUE_LENGTH_EXCEEDED_PREFIX + "first name" + MESSAGE_VALUE_LENGTH_EXCEEDED_SUFFIX + 100)
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String additionalInformation;

    private long created;
    private long modified;

    public Person(String person_uuid, String first_name, String last_name, Date birthdate) {
        setPersonUUID(person_uuid);
        setFirstName(first_name);
        setLastName(last_name);
        setBirthdate(birthdate);
        setCreated(System.currentTimeMillis());
        setModified(System.currentTimeMillis());
    }

    public Person() {
        setCreated(System.currentTimeMillis());
        setModified(System.currentTimeMillis());
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

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getModified() {
        return modified;
    }

    public void setModified(long modified) {
        this.modified = modified;
    }

    public String createUUID() {
        String uuid = UUID.randomUUID().toString();
        this.setPersonUUID(uuid);
        return uuid;
    }

}
