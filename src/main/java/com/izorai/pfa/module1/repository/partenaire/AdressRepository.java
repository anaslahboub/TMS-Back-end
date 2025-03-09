package com.izorai.pfa.module1.repository.partenaire;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {
}


