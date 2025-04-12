package com.izorai.pfa.module1.repository.partenaire;

import com.izorai.pfa.module1.entities.partenaire.Partenaire;
import com.izorai.pfa.module1.entities.partenaire.Physique;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysiqueRepository extends JpaRepository<Physique, Long> {
    Physique findByAdressesIdAdress(Long adressesId);
    List<Physique> findAllByTypePartenaire(TypePartenaire typePartenaire);

}
