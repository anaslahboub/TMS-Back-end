package com.izorai.pfa.module2.repository.marchandises;

import com.izorai.pfa.module2.entities.marchandises.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Optional<Categorie> findByLibelle(String categorie);
    boolean existsByLibelle(String categorie);
}