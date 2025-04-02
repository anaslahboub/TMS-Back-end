package com.izorai.pfa.module1.services.camion.entretien;

import com.izorai.pfa.module1.DTO.camion.entretien.EntretienDTO;
import com.izorai.pfa.module1.entities.camion.Entretien;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EntrtienService {

    ///  ENTRETIEN CRUD /
    public EntretienDTO createEntretien(EntretienDTO entretien);
    public List<EntretienDTO> getAllEntretiens();
    public Optional<EntretienDTO> getEntretienById(Long id);
    public EntretienDTO updateEntretien(Long id, EntretienDTO entretienDetails);
    public void deleteEntretien(Long id);

    List<EntretienDTO> getEntretiensByCamion(String immatriculationCamion);
    List<EntretienDTO> getEntretiensByDateRange(LocalDate debut, LocalDate fin);
    List<EntretienDTO> getEntretiensByType(String typeEntretien);


    // Statistiques
    double getCoutTotalEntretiensByCamion(String immatriculationCamion  );
    double getCoutTotalEntretiensByPeriode(LocalDate debut, LocalDate fin);

    double calculateCoutEntretiens(String immatriculation);


}
