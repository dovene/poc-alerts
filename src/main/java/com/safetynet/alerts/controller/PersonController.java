package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.DTO.PersonDTO;
import com.safetynet.alerts.model.core.Person;
import com.safetynet.alerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private static Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public List<Person> listPersons() {
        logger.info("Ask Get persons");
        logger.info("Response : " + personService.findAll());
        return personService.findAll();
    }

    @GetMapping(value = "/persons/{firstName}")
    public List<Person> returnOnePerson(@PathVariable String firstName) {
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        logger.info("Ask GET one person with firstName = " + firstName);
        logger.info("Response : " + personService.findByFirstName(firstName));
        return personService.findByFirstName(firstName);
    }

    @GetMapping(value = "communityEmail", produces = "application/json")
    public List<String> getCommunityEmail(@RequestParam(value = "city") String city) {
        logger.info("Ask Get all email from person to live in city : " + city);
        logger.info("Response : " + personService.findEmailByCity(city));
        return personService.findEmailByCity(city);
    }

}
