package com.izorai.pfa.module1.services.camion.autre;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IPlanificationService {
    // Planification
    void planifierEntretiensPeriodiques();
    void planifierRenouvellementAssurances();

    // Alertes
    List<Map<String, Object>> getAlertesMaintenance();
    List<Map<String, Object>> getAlertesDocuments();
    List<Map<String, Object>> getAlertesKilometrages();

    // Notifications
    void envoyerNotificationsMaintenanceProgrammee();
    void envoyerNotificationsDocumentsARenouveler();
}