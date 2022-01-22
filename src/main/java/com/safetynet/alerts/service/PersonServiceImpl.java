package com.safetynet.alerts.service;

import com.safetynet.alerts.manager.PersonMapper;
import com.safetynet.alerts.model.DTO.PersonDTO;
import com.safetynet.alerts.model.core.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonServiceImpl implements PersonService {

    private static Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;

    @Override
    public List<Person> findAll() {
        logger.debug("PersonService findAll");
        if (personRepository.getPersonList().isEmpty()) {
            logger.warn("Persons list is empty");
        }
        return personMapper.mapDtoToDomainPersonList(personRepository.getPersonList());
    }

    @Override
    public List<Person> findByFirstName(String firstName) {
        logger.debug("PersonService findByFirstName");
        List<PersonDTO> personDTOList = new ArrayList<>();
        for (PersonDTO personDTO : personRepository.getPersonList()) {
            if (personDTO.getFirstName().equals(firstName)) {
                personDTOList.add(new PersonDTO(
                        personDTO.getFirstName(),
                        personDTO.getLastName(),
                        personDTO.getAddress(),
                        personDTO.getCity(),
                        personDTO.getZip(),
                        personDTO.getPhone(),
                        personDTO.getEmail()
                ));
            }
        }
        return personMapper.mapDtoToDomainPersonList(personDTOList);
    }

    @Override
    public List<Person> findByAddress(String address) {
        List<PersonDTO> personDTOList = new ArrayList<>();
        for (PersonDTO personDTO : personRepository.getPersonList()) {
            if (personDTO.getAddress().equals(address)) {
                personDTOList.add(new PersonDTO(
                        personDTO.getFirstName(),
                        personDTO.getLastName(),
                        personDTO.getAddress(),
                        personDTO.getCity(),
                        personDTO.getZip(),
                        personDTO.getPhone(),
                        personDTO.getEmail()
                ));
            }
        }
        return personMapper.mapDtoToDomainPersonList(personDTOList);
    }

    @Override
    public List<String> findEmailByCity(String city) {
        List<String> emailList = new ArrayList<>();
        for (PersonDTO personDTO : personRepository.getPersonList()) {
            if (personDTO.getCity().equals(city)) {
                if (emailList.contains(personDTO.getEmail())) {
                    continue;
                } else {
                    emailList.add(personDTO.getEmail());
                }
            }
        }
        return emailList;
    }
}
