package com.izorai.pfa.module1.repository.camion;

import com.izorai.pfa.module1.entities.camion.CarteGrise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteGriseRepository extends JpaRepository<CarteGrise, Long> {
}
