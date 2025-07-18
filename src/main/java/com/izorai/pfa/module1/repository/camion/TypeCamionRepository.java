package com.izorai.pfa.module1.repository.camion;

import com.izorai.pfa.module1.entities.camion.TypeCamion;
import com.izorai.pfa.module1.entities.camion.TypeCarburant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeCamionRepository extends JpaRepository<TypeCamion, Long> {
    Optional<TypeCarburant> findByType(String type);

}
