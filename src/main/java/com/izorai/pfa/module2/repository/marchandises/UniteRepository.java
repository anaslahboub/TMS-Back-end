package com.izorai.pfa.module2.repository.marchandises;

import com.izorai.pfa.module2.entities.marchandises.Unite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniteRepository extends JpaRepository<Unite, Long> {
    Optional<Unite> findByUnite(String unite);
    boolean existsByUnite(String unite);
}