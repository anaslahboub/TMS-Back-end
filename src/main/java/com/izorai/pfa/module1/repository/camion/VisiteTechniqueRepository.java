package com.izorai.pfa.module1.repository.camion;
import com.izorai.pfa.module1.entities.camion.VisiteTechnique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisiteTechniqueRepository extends JpaRepository<VisiteTechnique, Long> {
    List<VisiteTechnique> findByDateExpirationBefore(LocalDate date);
    List<VisiteTechnique> findByCamionImmatriculation(String immatriculation);
    List<VisiteTechnique> findByDateExpirationBetween(LocalDate start, LocalDate end);
}