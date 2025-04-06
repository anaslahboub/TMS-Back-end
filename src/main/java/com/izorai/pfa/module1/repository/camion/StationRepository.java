package com.izorai.pfa.module1.repository.camion;

import com.izorai.pfa.module1.entities.camion.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}
