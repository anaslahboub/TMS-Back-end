package com.izorai.pfa.module1.repository.camion;

import com.izorai.pfa.module1.entities.camion.Remorque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RemorqueRepository extends JpaRepository<Remorque, Long> {
    List<Remorque> getRemorquesByDisponible(boolean disponible);

}
