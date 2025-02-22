package com.izorai.pfa.module1.repository;

import com.izorai.pfa.module1.entities.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Long> {
}
