package com.izorai.pfa.module1.services.camion.autre;



import java.time.LocalDate;
import java.util.List;

public interface IDocumentService {
    // Vérification des documents
    boolean verifierValiditeAssurance(String immatriculationCamion);
    boolean verifierValiditeCarteGrise(String immatriculationCamion);

    // Alertes
    List<String> getCamionsAvecDocumentsExpirants(int joursAvant);
    List<String> getCamionsAvecDocumentsExpires();

    // Gestion des rappels
    void envoyerRappelsExpirationDocuments(int joursAvant);
}