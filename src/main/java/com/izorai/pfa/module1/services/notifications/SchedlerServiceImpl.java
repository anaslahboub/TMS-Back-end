package com.izorai.pfa.module1.services.notifications;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.izorai.pfa.module1.DTO.camion.assurance.AssuranceDTO;
import com.izorai.pfa.module1.entities.camion.Assurance;
import com.izorai.pfa.module1.entities.camion.CarteGrise;
import com.izorai.pfa.module1.entities.camion.Entretien;
import com.izorai.pfa.module1.entities.camion.VisiteTechnique;
import com.izorai.pfa.module1.services.camion.assurance.AssuranceService;
import com.izorai.pfa.module1.services.camion.cartegrise.CarteGriseService;
import com.izorai.pfa.module1.services.camion.entretien.EntrtienService;
import com.izorai.pfa.module1.services.camion.visiteTechnique.VisiteTechniqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class SchedlerServiceImpl implements SchedlerService {
    private final NotificationService notificationService;
    private final VisiteTechniqueService visiteTechniqueService;
    private final AssuranceService assuranceService;
    private final CarteGriseService carteGriseService;
    private final EntrtienService entrtienService;


    @Override
    @Scheduled(fixedRate =  2 * 60 * 1000L) // 30 days in milliseconds
    public void checkAndNotifyExpiringVisitesTechniques() {
        List<VisiteTechnique> expiringVisites = visiteTechniqueService.getVisitesTechniquesExpirantDans30Jours();
        for (VisiteTechnique visite : expiringVisites) {
            try {
                notificationService.envoyerNotificationVisiteTechnique(visite);
                System.out.println("Notification envoyée pour visite technique ID: " + visite.getId());
            } catch (Exception e) {
                System.err.println("Erreur lors de l'envoi de la notification pour visite technique ID " + visite.getId() + ": " + e.getMessage());
            }
        }
    }

    @Override
    @Scheduled(fixedRate =  2 * 60 * 1000L) // 30 days in milliseconds
    public void checkExpirationAssurances() {
        // Récupérer les assurances qui expirent dans moins de 30 jours
        List<Assurance> assurancesExpirant = assuranceService.getAssurancesExpirantDans30Jours() ;

        for (Assurance assurance : assurancesExpirant) {
            LocalDate today = LocalDate.now();
            long joursRestants = ChronoUnit.DAYS.between(today, assurance.getDateExpiration());

            // Envoyer une notification différente selon l'urgence
            if (joursRestants <= 7) {
                notificationService.envoyerNotificationAssurance(assurance,joursRestants);
            } else if (joursRestants <= 15) {
                notificationService.envoyerNotificationAssurance(assurance,joursRestants);
            } else {
                notificationService.envoyerNotificationAssurance(assurance,joursRestants);
            }
        }

    }

    @Override
    @Scheduled(fixedRate =  2 * 60 * 1000L)
    public void checkAndNotifyCarteGrise() {
        List<CarteGrise> carteGrises = carteGriseService.catreGriseExpireIn30Days();

        for (CarteGrise carteGrise : carteGrises) {
            try{
                notificationService.envoyerNotificationCartesGrises(carteGrise);
                System.out.println("Notification envoyée pour Carte Grise ID:" + carteGrise.getId());

            } catch (Exception e) {
                System.err.println("Erreurs de L'envoi de notification vers Carte Grise de id " +carteGrise.getId() +"avec le message :"+e.getMessage());
            }
        }

    }

    @Override
    public void checkAndNotifyEntretien() {
        List<Entretien> entretiens = entrtienService.getProchainsEntertiensIn30Days();

        for (Entretien entretien : entretiens) {
            try {
                notificationService.envoyerNotificationEntretien(entretien);
                System.out.println("Notification envyes pour entreniez de Id : " + entretien.getId());
            }catch (Exception e) {
                System.err.println("Erreurs de L'envoi de notifications de Entrtiens  de Id :"+entretien.getId() +"avec le message :"+e.getMessage()   );
            }
        }

    }
}
