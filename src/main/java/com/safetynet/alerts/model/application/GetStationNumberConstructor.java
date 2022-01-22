package com.safetynet.alerts.model.application;

import org.springframework.stereotype.Component;

import java.util.List;

public class GetStationNumberConstructor {

    List<GetStationNumberPerson> person;
    int nbAdult;
    int nbMinor;

    public GetStationNumberConstructor(List<GetStationNumberPerson> person, int nbAdult, int nbMinor) {
        this.person = person;
        this.nbAdult = nbAdult;
        this.nbMinor = nbMinor;
    }
}
