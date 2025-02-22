package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Truck;

import java.util.List;
import java.util.Optional;

public interface TruckService {
    Truck addNewTruck(Truck truck);
    List<Truck> getAllTrucks();
    Optional<Truck> getTruckById(Long immatriculation);
    Truck updateTruck(Long immatriculation, Truck truckDetails);
    void deleteTruck(Long immatriculation);
}
