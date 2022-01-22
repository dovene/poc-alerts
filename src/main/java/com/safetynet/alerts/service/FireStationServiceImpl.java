package com.safetynet.alerts.service;

import com.safetynet.alerts.model.application.GetStationNumberConstructor;
import com.safetynet.alerts.model.application.GetStationNumberPerson;
import com.safetynet.alerts.model.DTO.FireStationDTO;
import com.safetynet.alerts.model.DTO.MedicalRecordDTO;
import com.safetynet.alerts.model.DTO.PersonDTO;
import com.safetynet.alerts.model.core.Person;
import com.safetynet.alerts.repository.FireStationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FireStationServiceImpl implements FireStationService {

    private static final Logger logger = LogManager.getLogger(FireStationService.class);

    @Autowired
    private FireStationRepository fireStationRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    public FireStationServiceImpl(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    private boolean isAdult(LocalDate birthdate) {
        logger.debug("FireStationService isAdult");
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthdate, today);
        logger.debug(age.getYears());
        if (age.getYears() > 18) {
            return true;
        }
        return false;
    }

    @Override
    public List<FireStationDTO> findAll() {
        logger.debug("FireStationService findAll");
        if (fireStationRepository.getFireStationList().isEmpty()) {
            logger.warn("Fire Stations list is empty");
        }
        return fireStationRepository.getFireStationList();
    }

    @Override
    public List<GetStationNumberConstructor> findPersonCoverageByFireStation(int stationNumber) {
        logger.debug("FireStationService findPersonCoverageByFireStation");

        List<Person> personDTOS;
        List<GetStationNumberPerson> personList = new ArrayList<>();
        List<MedicalRecordDTO> medicalRecordDTOS;
        List<GetStationNumberConstructor> personNbAdultAndNbMinor = new ArrayList<>();
        String firstName;
        String lastName;
        String address;
        String phone;
        LocalDate birthdate;
        int numberAdults = 0;
        int numberMinors = 0;

        for (FireStationDTO fireStationDTO : fireStationRepository.getFireStationList()) {
            if (fireStationDTO.getStation() == stationNumber) {
                personDTOS = personService.findByAddress(fireStationDTO.getAddress());
                logger.debug("Récupe de personDTO : " + personDTOS.toString());
                for (Person person : personDTOS) {
                    firstName = person.getFirstName();
                    lastName = person.getLastName();
                    address = person.getAddress();
                    phone = person.getPhone();

                    medicalRecordDTOS = medicalRecordService.findByFirstName(firstName);
                    birthdate = medicalRecordDTOS.get(0).getBirthdate();

                    if (isAdult(birthdate)) {
                        numberAdults ++;
                    } else {
                        numberMinors ++;
                    }

                    personList.add(new GetStationNumberPerson(firstName, lastName, address, phone));
                }

            }
        }
        System.out.println("Number of adults : " + numberAdults);
        System.out.println("Number of minors : " + numberMinors);
        personNbAdultAndNbMinor.add(new GetStationNumberConstructor(personList,numberAdults,numberMinors));
        return personNbAdultAndNbMinor;
    }
}
