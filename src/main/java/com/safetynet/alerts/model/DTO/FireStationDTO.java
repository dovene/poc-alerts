package com.safetynet.alerts.model.DTO;

import org.springframework.stereotype.Component;

//TODO JavaDoc
@Component
public class FireStationDTO {

    private String address;
    private int station;

    public FireStationDTO() {
    }

    public FireStationDTO(String address, int station) {
        this.address = address;
        this.station = station;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "FireStations{" +
                "address='" + address + '\'' +
                ", station=" + station +
                '}';
    }
}
