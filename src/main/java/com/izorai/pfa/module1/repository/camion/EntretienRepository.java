package com.izorai.pfa.module1.repository.camion;

import com.izorai.pfa.module1.entities.camion.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EntretienRepository extends JpaRepository<Entretien, Long> {
    List<Entretien> findByDateEntretienBetween(LocalDate startDate, LocalDate endDate);
    List<Entretien> findByTypeEntretien(String typeEntretien);
    List<Entretien> findByCamionImmatriculation(String camionImmatriculation);
}
