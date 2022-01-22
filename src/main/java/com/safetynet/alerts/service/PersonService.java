package com.safetynet.alerts.service;

import com.safetynet.alerts.model.core.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();
    List<Person> findByFirstName(String firstName);
    List<Person> findByAddress(String address);
    List<String> findEmailByCity(String city);
}
