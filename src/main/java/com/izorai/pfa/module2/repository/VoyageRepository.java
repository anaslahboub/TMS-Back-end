package com.izorai.pfa.module2.repository;

import com.izorai.pfa.module2.entities.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Integer> {
}
