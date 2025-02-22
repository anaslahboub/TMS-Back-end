package com.izorai.pfa.module1.repository;

import com.izorai.pfa.module1.entities.Chaufeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChaufeurRepository extends JpaRepository<Chaufeur, Long> {
}
