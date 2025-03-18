package com.izorai.pfa.module1.repository.partenaire;

import com.izorai.pfa.module1.entities.partenaire.Morale;
import com.izorai.pfa.module1.entities.partenaire.Physique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoraleRepository extends JpaRepository<Morale, Long> {
    Morale findByAdressesIdAdress(Long adressesId);

}
