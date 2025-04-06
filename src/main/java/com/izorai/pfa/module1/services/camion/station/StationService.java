package com.izorai.pfa.module1.services.camion.station;

import com.izorai.pfa.module1.entities.camion.Station;

import java.util.List;

public interface StationService {
    public Station create(Station stationDto);
    public Station getStationById(Long id);
    public List<Station> getAllStations();
    public Station update(Long id ,Station stationDto);
    public void delete(Long id);
}
