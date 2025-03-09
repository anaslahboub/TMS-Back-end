package com.izorai.pfa.module1.services.camion.autre;

import java.time.LocalDate;
import java.util.Map;

public interface IStatistiquesCamionService {
    Map<String, Double> getCoutsTotauxParCamion(LocalDate debut, LocalDate fin);
    Map<String, Double> getCoutsCarburantParCamion(LocalDate debut, LocalDate fin);
    Map<String, Double> getCoutsEntretienParCamion(LocalDate debut, LocalDate fin);

    // Performance
    Map<String, Double> getConsommationMoyenneParCamion();
    Map<String, Integer> getKilometragesParcourusParCamion(LocalDate debut, LocalDate fin);

    // Disponibilité
    double getTauxDisponibiliteCamion(String immatriculationCamion, LocalDate debut, LocalDate fin);

    // Rapports
    byte[] genererRapportEtatFlotte();
    byte[] genererRapportCoutsMensuels(int mois, int annee);
    byte[] genererRapportEntretiensProgrammes();
}
