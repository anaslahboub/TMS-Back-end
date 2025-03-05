package com.izorai.pfa.module1.repository;

import com.izorai.pfa.module1.entities.camion.Remorque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RemorqueRepository extends JpaRepository<Remorque, Long> {
    Optional<Remorque> findByImmatriculation(String immatriculation);

    void deleteByImmatriculation(String immatriculation);
}
