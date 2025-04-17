package com.izorai.pfa.module2.repository;

import com.izorai.pfa.module2.entities.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Long> {
    List<Voyage> findByDateDepartBetween(LocalDateTime start, LocalDateTime end);
    List<Voyage> findByEtat(String statut);
    List<Voyage> findAllByDateArrivePrevueBetween(LocalDate end, LocalDate start);

}