package com.izorai.pfa.module1.repository.partenaire;

import com.izorai.pfa.module1.entities.partenaire.Morale;
import com.izorai.pfa.module1.entities.partenaire.Physique;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoraleRepository extends JpaRepository<Morale, Long> {
    Morale findByAdressesIdAdress(Long adressesId);
    List<Morale> findAllByTypePartenaire(TypePartenaire typePartenaire);

}
