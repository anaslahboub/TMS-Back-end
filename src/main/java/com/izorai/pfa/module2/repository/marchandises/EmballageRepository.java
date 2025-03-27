package com.izorai.pfa.module2.repository.marchandises;

import com.izorai.pfa.module2.entities.marchandises.Emballage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmballageRepository extends JpaRepository<Emballage, Long> {
    Optional<Emballage> findByNom(String nom);
    boolean existsByNom(String nom);
}