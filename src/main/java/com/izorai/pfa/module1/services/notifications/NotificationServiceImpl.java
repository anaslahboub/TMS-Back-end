package com.izorai.pfa.module1.services.notifications;

import com.izorai.pfa.module1.entities.camion.Assurance;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Entretien;
import com.izorai.pfa.module1.repository.camion.CamionRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;
    private final CamionRepository camionRepository;

    @Value("${notification.email.admin}")
    private String adminEmail;

    @Value("${notification.email.from}")
    private String emailFrom;


    @Override
    public void envoyerNotificationRenouvellement(Assurance assurance) {
        Camion camion = camionRepository.findByAssurance(assurance);
        String sujet = "Confirmation de renouvellement d'assurance - " + camion.getImmatriculation();
        String contenu = buildEmailContent(
                "Renouvellement d'assurance confirmé",
                "L'assurance du camion immatriculé " +camion.getImmatriculation() + " a été renouvelée avec succès.",
                "Numéro de contrat: " + assurance.getNumeroContrat(),
                "Nouvelle date d'expiration: " + assurance.getDateDebut(),
                "Prime annuelle: " + assurance.getPrimeAnnuelle() + " €"
        );

        envoyerEmail(adminEmail, sujet, contenu);
    }
    @Override
    public void envoyerAlerteStandard(Assurance assurance,Long joursRestants) {
        Camion camion = camionRepository.findByAssurance(assurance);
        String sujet = "Alerte: Assurance expirant dans " + joursRestants + " jours - " + camion.getImmatriculation();
        String contenu = buildEmailContent(
                "Alerte d'expiration d'assurance",
                "L'assurance du camion immatriculé " + camion.getImmatriculation() + " expire dans " + joursRestants + " jours.",
                "Numéro de contrat: " + assurance.getNumeroContrat(),
                "Date d'expiration: " + assurance.getDateExpiration(),
                "Prime annuelle: " + assurance.getPrimeAnnuelle() + " €"
        );

        envoyerEmail(adminEmail, sujet, contenu);
    }

    @Override
    public void notifierExpirationAssurance(Assurance assurance) {
        Camion camion = camionRepository.findByAssurance(assurance);
        String sujet = "ALERTE MAJEURE: Assurance EXPIRÉE - " + camion.getImmatriculation();
        String contenu = buildEmailContent(
                "Assurance EXPIRÉE",
                "⚠️ L'assurance du camion immatriculé " + camion.getImmatriculation() + " est maintenant EXPIRÉE!",
                "Numéro de contrat: " + assurance.getNumeroContrat(),
                "Date d'expiration: " + assurance.getDateExpiration(),
                "LE VÉHICULE NE DOIT PLUS CIRCULER! Ce camion n'est plus couvert par une assurance valide.",
                "Action requise immédiatement pour éviter des sanctions légales et financières!"
        );

        // Notification critique à tous les responsables
        envoyerEmail(adminEmail, sujet, contenu);
    }

    @Override
    public void envoyerNotificationEntretien(Entretien entretien) {
        Camion camion = entretien.getCamion();

        String sujet = "Rappel: Entretien programmé pour le camion " + camion.getImmatriculation();
        String contenu = buildEmailContent(
                "Entretien Programmé",
                "Un entretien est prévu pour le camion immatriculé " + camion.getImmatriculation(),
                "Date de l'entretien : " + entretien.getDateEntretien(),
                "Type d'entretien : " + entretien.getTypeEntretien(),
                "Description : " + entretien.getDescription(),
                "Veuillez vous assurer que le camion est disponible pour cet entretien."
        );

        envoyerEmail(adminEmail, sujet, contenu);
    }



    // Méthode utilitaire pour envoyer un email
    private void envoyerEmail(String destinataire, String sujet, String contenu) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(emailFrom);
            helper.setTo(destinataire);
            helper.setSubject(sujet);
            helper.setText(contenu, true); // true pour indiquer que c'est du HTML

            mailSender.send(message);

            // Log de l'envoi d'email réussi
            System.out.println("Email envoyé à " + destinataire + " avec le sujet: " + sujet);

        } catch (MessagingException e) {
            // Gestion des erreurs d'envoi d'email
            System.err.println("Erreur lors de l'envoi de l'email à " + destinataire + ": " + e.getMessage());
            e.printStackTrace();
            // Considérer l'utilisation d'un système de log plus robuste comme SLF4J/Logback
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
            html.append("<p>").append(paragraphe).append("</p>");
        }

        html.append("<div class=\"footer\">");
        html.append("<p>Ce message est généré automatiquement. Merci de ne pas y répondre.</p>");
        html.append("<p>© ").append(java.time.Year.now().getValue()).append(" Votre Entreprise. Tous droits réservés.</p>");
        html.append("</div></div></body></html>");

        return html.toString();
    }
}
