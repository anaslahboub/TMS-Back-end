package com.izorai.pfa.module1.repository.camion;

import com.izorai.pfa.module1.entities.camion.Carburant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarburantRepository extends JpaRepository<Carburant, Long> {


    List<Carburant> findByDateRemplissageBetween(LocalDate debut, LocalDate fin);

}
