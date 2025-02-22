package com.izorai.pfa.module1.repository;

import com.izorai.pfa.module1.entities.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Long> {
}
