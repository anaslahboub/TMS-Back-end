package com.izorai.pfa.module1.repository;

import com.izorai.pfa.module1.entities.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntretienRepository extends JpaRepository<Entretien, Long> {
}
