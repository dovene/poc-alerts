package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.application.GetStationNumberConstructor;
import com.safetynet.alerts.model.DTO.FireStationDTO;
import com.safetynet.alerts.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FireStationController {

    private Logger logger = LogManager.getLogger(FireStationController.class);

    @Autowired
    private FireStationService fireStationService;

    @GetMapping("/firestations")
    public List<FireStationDTO> listFireStation() {
        logger.info("Ask GET firestations");
        logger.info("Response : " + fireStationService.findAll());
        return fireStationService.findAll();
    }

    @GetMapping("/firestation")
    public List<GetStationNumberConstructor> getPersonCoverageByAFireStation(@RequestParam(value = "stationNumber") int stationNumber) {
        logger.info("Ask GET firestation?stationNumber=" + stationNumber);
        logger.info("Response : " + fireStationService.findPersonCoverageByFireStation(stationNumber));
        return fireStationService.findPersonCoverageByFireStation(stationNumber);
    }

}
