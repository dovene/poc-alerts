package com.safetynet.alerts.service;

import com.safetynet.alerts.model.application.GetStationNumberConstructor;
import com.safetynet.alerts.model.DTO.FireStationDTO;

import java.util.List;

public interface FireStationService {

    List<FireStationDTO> findAll();
    List<GetStationNumberConstructor> findPersonCoverageByFireStation(int stationNumber);
}
