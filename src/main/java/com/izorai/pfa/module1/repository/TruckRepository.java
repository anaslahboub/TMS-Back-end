package com.izorai.pfa.module1.repository;

import com.izorai.pfa.module1.entities.camion.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {
    Optional<Truck> findByImmatriculation(String immatriculation);

    void deleteByImmatriculation(String immatriculation);
}
