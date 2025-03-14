package com.izorai.pfa.module1.repository.partenaire;

import com.izorai.pfa.module1.entities.camion.Chaufeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChaufeurRepository extends JpaRepository<Chaufeur, Long> {
    Chaufeur findByIdPartenaire(Long idPartenaire);
}
