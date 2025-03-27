package com.izorai.pfa.module2.repository.marchandises;

import com.izorai.pfa.module2.entities.marchandises.Marchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarchandiseRepository extends JpaRepository<Marchandise, Long> {
    Optional<Marchandise> findByCodeMarchandise(String code);
    boolean existsByCodeMarchandise(String code);
}