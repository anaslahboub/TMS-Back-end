package com.izorai.pfa.module1.services.notifications;

import com.izorai.pfa.module1.entities.camion.Assurance;
import com.izorai.pfa.module1.entities.camion.Entretien;

public interface NotificationService {
    public void envoyerNotificationRenouvellement(Assurance assurance);
    public void envoyerAlerteStandard(Assurance assurance,Long joursRestants) ;
    public void notifierExpirationAssurance(Assurance assurance);
    public void envoyerNotificationEntretien(Entretien entretien);



    }
