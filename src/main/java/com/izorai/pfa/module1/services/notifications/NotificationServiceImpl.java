package com.izorai.pfa.module1.services.notifications;

import com.izorai.pfa.module1.entities.camion.*;
import com.izorai.pfa.module1.entities.enumerations.NotificationFrom;
import com.izorai.pfa.module1.repository.NotificationRepository;
import com.izorai.pfa.module1.repository.camion.CamionRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;
    private final CamionRepository camionRepository;
    private final String adminEmail;
    private final String emailFrom;
    private final String emailFromName;
    private final NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        Notification oldNotification = notificationRepository.findById(notification.getId()).orElse(null);
        if (notification.getContent() != null) {
            oldNotification.setContent(notification.getContent());
        }
        if (notification.getTitle() != null) {
            oldNotification.setTitle(notification.getTitle());
        }
        if (notification.getNotificationFrom() != null) {
            oldNotification.setNotificationFrom(notification.getNotificationFrom());
        }
        if (notification.getDateEnvoie() != null) {
            oldNotification.setDateEnvoie(notification.getDateEnvoie());
        }
        notificationRepository.save(oldNotification);
        return notification;
    }

    @Override
    public void deleteNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(()->new RuntimeException("Notification not found"));
         notificationRepository.delete(notification);
    }

    @Override
    public Notification getNotification(Long notificationId) {
        return notificationRepository.findById(notificationId).orElseThrow(()-> new RuntimeException("Notification not found")  );
    }

    @Override
    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> getNotifications(NotificationFrom notificationFrom) {
        return notificationRepository.findAllByNotificationFrom(notificationFrom);
    }



    @Override
    public void envoyerNotificationAssurance(Assurance assurance, Long joursRestants) {
        Camion camion = camionRepository.findByAssurance(assurance);
        if (camion == null) {
            System.err.println("Camion non trouvé pour l'assurance: " + assurance.getNumeroContrat());
            return;
        }
        String sujet = "Alerte: Assurance expirant dans " + joursRestants + " jours - " + camion.getImmatriculation();
        String contenu = buildEmailContent(
                "Alerte d'expiration d'assurance",
                "L'assurance du camion immatriculé " + camion.getImmatriculation() + " expire dans " + joursRestants + " jours.",
                "Numéro de contrat: " + assurance.getNumeroContrat(),
                "Date d'expiration: " + assurance.getDateExpiration(),
                "Prime annuelle: " + assurance.getPrimeAnnuelle() + " €"
        );
       boolean statut = envoyerEmail(adminEmail, sujet, contenu);
       if (statut){
           Notification notification = new Notification();
           notification.setDateEnvoie(LocalDate.now());
           notification.setVu(false);
           notification.setNotificationFrom( NotificationFrom.ASSURANCE);
           notification.setTitle(sujet);
           notification.setContent(contenu);
           notificationRepository.save(notification);
       }
    }

    @Override
    public void envoyerNotificationEntretien(Entretien entretien) {
        Camion camion = entretien.getCamion();
        if (camion == null) {
            System.err.println("Camion non trouvé pour l'entretien: " + entretien.getId());
            return;
        }
        String sujet = "Rappel: Entretien programmé pour le camion " + camion.getImmatriculation();
        String contenu = buildEmailContent(
                "Entretien Programmé",
                "Un entretien est prévu pour le camion immatriculé " + camion.getImmatriculation(),
                "Date de l'entretien : " + entretien.getDateEntretien(),
                "Type d'entretien : " + entretien.getTypeEntretien(),
                "Description : " + entretien.getDescription(),
                "Veuillez vous assurer que le camion est disponible pour cet entretien."
        );
        boolean statut = envoyerEmail(adminEmail, sujet, contenu);
        if (statut) {
            Notification notification = new Notification();
            notification.setVu(false);
            notification.setContent(contenu);
            notification.setTitle(sujet);
            notification.setDateEnvoie(LocalDate.now());
            notification.setNotificationFrom(NotificationFrom.ENTRETIEN);
        }
    }

    @Override
    public void envoyerNotificationVisiteTechnique(VisiteTechnique visiteTechnique) {
        Camion camion = visiteTechnique.getCamion();
        if (camion == null) {
            System.err.println("Camion non trouvé pour la visite technique: " + visiteTechnique.getId());
            return;
        }
        String sujet = "Rappel: Visite Technique pour le camion " + camion.getImmatriculation();
        String contenu = buildEmailContent(
                "Visite Technique Planifiée",
                "Une visite technique est prévue pour le camion immatriculé " + camion.getImmatriculation() + ".",
                "Date de la visite: " + visiteTechnique.getDateVisite(),
                "Date d'expiration: " + visiteTechnique.getDateExpiration(),
                visiteTechnique.getResultatVisite() +
                "Veuillez vous assurer que le camion est prêt pour cette visite."
        );
        boolean statut = envoyerEmail(adminEmail, sujet, contenu);
        if (statut) {
            Notification notification = new Notification();
            notification.setVu(false);
            notification.setDateEnvoie(LocalDate.now());
            notification.setContent(contenu);
            notification.setTitle(sujet);
            notification.setNotificationFrom(NotificationFrom.VISITE_TECHNIQUE);
            notificationRepository.save(notification);
        }
    }

    @Override
    public void envoyerNotificationCartesGrises(CarteGrise carteGrise){
        Camion camion = camionRepository.findByCarteGriseId(carteGrise.getId());
        if (camion == null) {
            System.err.println("Camion non trouvé pour la visite technique: " + carteGrise.getId());
            return;
        }
        String sujet = "Rappel:  Carte Grise pour le camion " + camion.getImmatriculation();
        String contenu = buildEmailContent(
                "Carte Grise  Planifiée",
                "Une Carte Grise est prévue pour le camion immatriculé " + camion.getImmatriculation() + ".",
                "Date de la visite: " + carteGrise.getDateMiseEnCirculation(),
                "Date d'expiration: " + carteGrise.getDateDelivrance(),
                "Veuillez vous assurer que le camion a renouveller cette cette visite Sooon."
        );
        boolean statut = envoyerEmail(adminEmail, sujet, contenu);
        if (statut) {
            Notification notification = new Notification();
            notification.setVu(false);
            notification.setContent(contenu);
            notification.setTitle(sujet);
            notification.setDateEnvoie(LocalDate.now());
            notification.setNotificationFrom(NotificationFrom.CARTE_GRISE);
            notificationRepository.save(notification);
        }
    }

    // Méthode utilitaire pour envoyer un email
    private Boolean envoyerEmail(String destinataire, String sujet, String contenu) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(emailFromName + " <" + emailFrom + ">");
            helper.setTo(destinataire);
            helper.setSubject(sujet);
            helper.setText(contenu, true); // true indicates HTML
            mailSender.send(message);
            System.out.println("Email envoyé à " + destinataire + " avec le sujet: " + sujet);
            return true;
        } catch (MessagingException e) {
            System.err.println("Erreur lors de l'envoi de l'email à " + destinataire + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour construire le contenu HTML de l'email
    private String buildEmailContent(String titre, String... paragraphes) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><style>");
        html.append("body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }");
        html.append("h1 { color: #0056b3; }");
        html.append(".container { max-width: 600px; margin: 0 auto; padding: 20px; }");
        html.append(".footer { margin-top: 30px; font-size: 12px; color: #777; border-top: 1px solid #ddd; padding-top: 10px; }");
        html.append("</style></head><body><div class=\"container\">");
        html.append("<h1>").append(titre).append("</h1>");
        for (String paragraphe : paragraphes) {
            if (paragraphe != null) { // Skip null paragraphs
                html.append("<p>").append(paragraphe).append("</p>");
            }
        }
        html.append("<div class=\"footer\">");
        html.append("<p>Ce message est généré automatiquement. Merci de ne pas y répondre.</p>");
        html.append("<p>© ").append(java.time.Year.now().getValue()).append(" Votre Entreprise. Tous droits réservés.</p>");
        html.append("</div></div></body></html>");
        return html.toString();
    }


    @Override
    public void envoyerNotificationRenouvellement(Assurance assurance) {
        Camion camion = camionRepository.findByAssurance(assurance);
        if (camion == null) {
            System.err.println("Camion non trouvé pour l'assurance: " + assurance.getNumeroContrat());
            return;
        }
        String sujet = "Confirmation de renouvellement d'assurance - " + camion.getImmatriculation();
        String contenu = buildEmailContent(
                "Renouvellement d'assurance confirmé",
                "L'assurance du camion immatriculé " + camion.getImmatriculation() + " a été renouvelée avec succès.",
                "Numéro de contrat: " + assurance.getNumeroContrat(),
                "Nouvelle date d'expiration: " + assurance.getDateDebut(),
                "Prime annuelle: " + assurance.getPrimeAnnuelle() + " €"
        );
        envoyerEmail(adminEmail, sujet, contenu);
    }

    @Override
    public void notifierExpirationAssurance(Assurance assurance) {
        Camion camion = camionRepository.findByAssurance(assurance);
        if (camion == null) {
            System.err.println("Camion non trouvé pour l'assurance: " + assurance.getNumeroContrat());
            return;
        }
        String sujet = "ALERTE MAJEURE: Assurance EXPIRÉE - " + camion.getImmatriculation();
        String contenu = buildEmailContent(
                "Assurance EXPIRÉE",
                "⚠️ L'assurance du camion immatriculé " + camion.getImmatriculation() + " est maintenant EXPIRÉE!",
                "Numéro de contrat: " + assurance.getNumeroContrat(),
                "Date d'expiration: " + assurance.getDateExpiration(),
                "LE VÉHICULE NE DOIT PLUS CIRCULER! Ce camion n'est plus couvert par une assurance valide.",
                "Action requise immédiatement pour éviter des sanctions légales et financières!"
        );
        envoyerEmail(adminEmail, sujet, contenu);
    }


}