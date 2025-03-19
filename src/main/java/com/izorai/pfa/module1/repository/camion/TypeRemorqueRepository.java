package com.izorai.pfa.module1.repository.camion;

import com.izorai.pfa.module1.entities.camion.TypeCarburant;
import com.izorai.pfa.module1.entities.camion.TypeRemorque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRemorqueRepository extends JpaRepository<TypeRemorque, Long> {
    Optional<TypeCarburant> findByType(String type);
}
