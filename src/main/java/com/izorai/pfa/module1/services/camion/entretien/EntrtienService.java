package com.izorai.pfa.module1.services.camion.entretien;

import com.izorai.pfa.module1.DTO.camion.camion.CamionDTO;
import com.izorai.pfa.module1.DTO.camion.entretien.EntretienDTO;
import com.izorai.pfa.module1.DTO.camion.entretien.EntretienViewResp;
import com.izorai.pfa.module1.entities.camion.Entretien;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EntrtienService {

    ///  ENTRETIEN CRUD /
    public EntretienDTO createEntretien(EntretienDTO entretien);
    public List<EntretienViewResp> getAllEntretiens();
    public Optional<EntretienViewResp> getEntretienById(Long id);
    public EntretienDTO updateEntretien(Long id, EntretienDTO entretienDetails);
    public void deleteEntretien(Long id);


    public CamionDTO getCamionByIdEntretiens(Long id);

    List<EntretienViewResp> getEntretiensByCamion(String immatriculationCamion);
    List<EntretienViewResp> getEntretiensByDateRange(LocalDate debut, LocalDate fin);
    List<EntretienViewResp> getEntretiensByType(String typeEntretien);


    // Statistiques
    double getCoutTotalEntretiensByCamion(String immatriculationCamion  );
    double getCoutTotalEntretiensByPeriode(LocalDate debut, LocalDate fin);

    double calculateCoutEntretiens(String immatriculation);


}
