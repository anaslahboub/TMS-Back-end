package com.izorai.pfa.module1.repository.camion;

import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Carburant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarburantRepository extends JpaRepository<Carburant, Long> {

    @Query("SELECT c FROM Carburant c WHERE c.camion.immatriculation = :immatriculation ORDER BY c.dateRemplissage DESC LIMIT 1")
    Optional<Carburant> findLastCarburantByCamion(@Param("immatriculation") String immatriculation);

    List<Carburant> findByDateRemplissageBetween(LocalDate debut, LocalDate fin);
    @Query("SELECT AVG(c.consommation) FROM Carburant c WHERE c.consommation IS NOT NULL")
    Double findAverageConsommation();
    @Query("SELECT COALESCE(SUM(c.quantiteLitres), 0) FROM Carburant c")
    double sumQuantiteLitres();

    @Query("SELECT AVG(c.prixParLitre) FROM Carburant c WHERE c.prixParLitre IS NOT NULL")
    Optional<Double> avgPrixParLitre();

    @Query("SELECT c.kilometrageActuel FROM Carburant c WHERE c.camion.immatriculation = :immatriculation ORDER BY c.dateRemplissage DESC LIMIT 1")
    Optional<Double> findLastKilometrageByCamionImmatriculation(@Param("immatriculation") String immatriculation);
    void deleteAllByCamion(Camion camion);



}
