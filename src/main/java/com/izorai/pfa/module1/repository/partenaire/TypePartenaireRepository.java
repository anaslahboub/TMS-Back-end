package com.izorai.pfa.module1.repository.partenaire;

import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePartenaireRepository extends JpaRepository<TypePartenaire, Long> {
}
