package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Truck;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TruckServiceImpl implements TruckService {
    @Override
    public Truck addNewTruck(Truck truck) {
        return null;
    }

    @Override
    public List<Truck> getAllTrucks() {
        return List.of();
    }

    @Override
    public Optional<Truck> getTruckById(Long immatriculation) {
        return Optional.empty();
    }

    @Override
    public Truck updateTruck(Long immatriculation, Truck truckDetails) {
        return null;
    }

    @Override
    public void deleteTruck(Long immatriculation) {

    }
}
