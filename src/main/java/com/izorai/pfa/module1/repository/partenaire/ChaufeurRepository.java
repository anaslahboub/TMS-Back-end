package com.izorai.pfa.module1.repository.partenaire;

import com.izorai.pfa.module1.entities.camion.Chaufeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChaufeurRepository extends JpaRepository<Chaufeur, Long> {
    Chaufeur findByIdPartenaire(Long idPartenaire);
    /**
     * Find drivers by their availability status
     * @param disponibilite Availability status to filter
     * @return List of drivers with the specified availability
     */
    List<Chaufeur> findByDisponibilite(String disponibilite);
    int countByDisponibilite(String disponibilite);
    List<Chaufeur> findAllByDateExpirationPermisBetween(LocalDate from, LocalDate to);

}
