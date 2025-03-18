package com.izorai.pfa.module1.repository.partenaire;

import com.izorai.pfa.module1.entities.partenaire.Partenaire;
import com.izorai.pfa.module1.entities.partenaire.Physique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysiqueRepository extends JpaRepository<Physique, Long> {
    Physique findByAdressesIdAdress(Long adressesId);

}
