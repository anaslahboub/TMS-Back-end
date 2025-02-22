package com.izorai.pfa.module1.repository;

import com.izorai.pfa.module1.entities.marchandises.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
