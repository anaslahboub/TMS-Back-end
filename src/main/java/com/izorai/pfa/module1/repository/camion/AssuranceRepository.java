package com.izorai.pfa.module1.repository.camion;

import com.izorai.pfa.module1.entities.camion.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Long> {
    void deleteByNumeroContrat(Long numeroContrat);
    List<Assurance> findByActive(boolean active);
    List<Assurance> findByDateExpirationBeforeAndActive(LocalDate date, boolean active);
    List<Assurance> findByDateExpirationBetweenAndActive(LocalDate datefin,LocalDate date, boolean active);

}
