package com.izorai.pfa.module1.repository.camion;

import com.izorai.pfa.module1.entities.camion.Assurance;
import com.izorai.pfa.module1.entities.camion.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {
    Optional<Camion> findByImmatriculation(String immatriculation);
    boolean existsByImmatriculation(String immatriculation);
    void deleteByImmatriculation(String immatriculation);
    Camion findByAssurance(Assurance assurance);
    Camion findByCarteGriseId(Long carteGriseId);
    Camion findByAssuranceNumeroContrat(Long assuranceId);

}
