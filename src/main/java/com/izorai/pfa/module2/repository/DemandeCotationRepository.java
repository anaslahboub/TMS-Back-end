package com.izorai.pfa.module2.repository;

import com.izorai.pfa.module2.entities.DemandeCotation;
import com.izorai.pfa.module2.entities.enumeartions.StatusDemandeCotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeCotationRepository extends JpaRepository<DemandeCotation, Long> {
    List<DemandeCotation> findByStatut(StatusDemandeCotation status);
    List<DemandeCotation> findByTypeMarchandiseContainingIgnoreCase(String type);
    long countByStatut(StatusDemandeCotation status);
}
