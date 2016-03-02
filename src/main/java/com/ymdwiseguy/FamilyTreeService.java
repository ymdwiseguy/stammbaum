package com.ymdwiseguy;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class FamilyTreeService {

    private static final Logger LOGGER = getLogger(FamilyTreeService.class);

    private final FamilyTreeRepo familyTreeRepo;

    @Autowired
    public FamilyTreeService(final FamilyTreeRepo familyTreeRepo) {
        this.familyTreeRepo = familyTreeRepo;
    }

    public Optional<Person> getPerson(String uuid) {
        return familyTreeRepo.getPerson(uuid);
    }



    public HashMap<String, Person> getParents(String personUUID) {
        return familyTreeRepo.getListOfPersons(personUUID, "PARENT");
    }
}
