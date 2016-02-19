package com.ymdwiseguy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FamilyTreeService {

    private final FamilyTreeRepo familyTreeRepo;

    @Autowired
    public FamilyTreeService(final FamilyTreeRepo familyTreeRepo) {
        this.familyTreeRepo = familyTreeRepo;
    }

    public String render() {
        UUID uuid = UUID.fromString("73c30299-e6c7-475f-a68b-61d6eb9b65a2");
        return "<html><head><title>title</title></head><body>" +
                "<h1>service render</h1>" + this.renderPerson(uuid) +
                "<body></html>";
    }

    private String renderPerson(UUID uuid) {
        Optional<Person> person = familyTreeRepo.getPerson(uuid);
        if (person.isPresent()) {
            return "<p>Person: " + person.get().getFirstName() + " "+ person.get().getLastName() + "</p>";
        }
        return "<p>Person not found</p>";
    }
}
