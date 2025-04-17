package com.izorai.pfa.module2.repository;



import com.izorai.pfa.module2.entities.Voyage;
import com.izorai.pfa.module2.enumerations.EtatVoyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Long> {

    // Find voyages by status
    List<Voyage> findByEtat(EtatVoyage etat);

    List<Voyage> findAllByDateArrivePrevueBetween(LocalDate start, LocalDate end);

    // Find voyages between date range
    List<Voyage> findByDateDepartBetween(LocalDate startDate, LocalDate endDate);

    // Count voyages by status
    long countByEtat(EtatVoyage etat);

    // Custom query to find voyages with a specific chauffeur
    @Query("SELECT v FROM Voyage v WHERE v.chaufeur.idPartenaire = :chauffeurId")
    List<Voyage> findByChauffeurId(Long chauffeurId);

    // Custom query to find voyages with a specific camion
    @Query("SELECT v FROM Voyage v WHERE v.camion.immatriculation = :camionId")
    List<Voyage> findByCamionId(Long camionId);

    // Custom query to find voyages with a specific remorque
    @Query("SELECT v FROM Voyage v WHERE v.remorque.idRemorque = :remorqueId")
    List<Voyage> findByRemorqueId(Long remorqueId);

    // Custom query to find upcoming voyages
    @Query("SELECT v FROM Voyage v WHERE v.dateDepart >= CURRENT_DATE ORDER BY v.dateDepart ASC")
    List<Voyage> findUpcomingVoyages();
}