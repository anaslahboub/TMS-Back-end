package com.izorai.pfa.module1.repository;

import com.izorai.pfa.module1.entities.TypePartenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePartenaireRepository extends JpaRepository<TypePartenaire, Long> {
}
