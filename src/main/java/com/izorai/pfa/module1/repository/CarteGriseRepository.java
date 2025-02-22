package com.izorai.pfa.module1.repository;

import com.izorai.pfa.module1.entities.CarteGrise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteGriseRepository extends JpaRepository<CarteGrise, Long> {
}
