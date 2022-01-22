package com.safetynet.alerts.manager;

import com.safetynet.alerts.model.DTO.PersonDTO;
import com.safetynet.alerts.model.core.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    public Person mapDtoToDomainPerson(PersonDTO personDTO){
        Person person = new Person();
        person.setFirstName(personDTO.getFirstName());
        person.setAddress(personDTO.getAddress());
        person.setCity(personDTO.getCity());
        person.setEmail(personDTO.getEmail());
        person.setPhone(personDTO.getPhone());
        person.setZip(personDTO.getZip());
        return person;
    }

    public List<Person> mapDtoToDomainPersonList(List<PersonDTO> personDTOArrayList){
        return personDTOArrayList.stream().map(personDTO -> mapDtoToDomainPerson(personDTO)).collect(Collectors.toList());
    }
}
