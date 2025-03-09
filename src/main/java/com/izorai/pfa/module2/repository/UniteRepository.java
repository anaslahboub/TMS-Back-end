package com.izorai.pfa.module2.repository;

import com.izorai.pfa.module2.entities.marchandises.Unite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniteRepository extends JpaRepository<Unite, Long> {
}
