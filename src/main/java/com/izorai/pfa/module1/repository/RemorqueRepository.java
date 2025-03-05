package com.izorai.pfa.module1.repository;

import com.izorai.pfa.module1.entities.camion.Remorque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemorqueRepository extends JpaRepository<Remorque, Long> {
}
