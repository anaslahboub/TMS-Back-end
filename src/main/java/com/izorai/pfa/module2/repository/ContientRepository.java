package com.izorai.pfa.module2.repository;

import com.izorai.pfa.module2.entities.contient.Contient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContientRepository extends JpaRepository<Contient, Long> {


    // Find all contient records for a specific marchandise
    @Query("SELECT c FROM Contient c WHERE c.marchandise.id = :marchandiseId")
    List<Contient> findByMarchandiseId(Long marchandiseId);

    // Custom query to find contient records with quantity greater than specified
    @Query("SELECT c FROM Contient c WHERE c.quantite > :minQuantite")
    List<Contient> findByQuantiteGreaterThan(long minQuantite);

    // Custom query to find contient records with quantity less than specified
    @Query("SELECT c FROM Contient c WHERE c.quantite < :maxQuantite")
    List<Contient> findByQuantiteLessThan(long maxQuantite);

    // Custom query to find contient records within quantity range
    @Query("SELECT c FROM Contient c WHERE c.quantite BETWEEN :minQuantite AND :maxQuantite")
    List<Contient> findByQuantiteBetween(long minQuantite, long maxQuantite);
}