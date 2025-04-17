package com.izorai.pfa.module1.services.notifications;

import com.izorai.pfa.module1.entities.camion.*;
import com.izorai.pfa.module1.entities.enumerations.NotificationFrom;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NotificationService {

    public Notification createNotification(Notification notification);
    public Notification updateNotification(Notification notification);
    public void deleteNotification(Long notificationId);
    public Notification getNotification(Long notificationId);
    public List<Notification> getNotifications();
    public List<Notification> getNotifications(NotificationFrom notificationFrom);




    public void envoyerNotificationRenouvellement(Assurance assurance);
    public void envoyerNotificationAssurance(Assurance assurance,Long joursRestants) ;
    public void notifierExpirationAssurance(Assurance assurance);
    public void envoyerNotificationEntretien(Entretien entretien);
    public void envoyerNotificationVisiteTechnique(VisiteTechnique visiteTechnique);
    public void envoyerNotificationCartesGrises(CarteGrise carteGrise);


    }
