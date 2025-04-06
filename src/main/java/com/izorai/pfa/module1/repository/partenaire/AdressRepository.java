package com.izorai.pfa.module1.repository.partenaire;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {
    Optional<Adress> findByRueAndVilleAndCodePostal(String rue, String ville, String codePostal);

}


