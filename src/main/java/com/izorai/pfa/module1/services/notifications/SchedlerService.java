package com.izorai.pfa.module1.services.notifications;

import com.izorai.pfa.module1.entities.camion.VisiteTechnique;

import java.util.List;

public interface SchedlerService {
    public void checkAndNotifyExpiringVisitesTechniques() ;
    public void checkExpirationAssurances() ;
    public void checkAndNotifyCarteGrise() ;
    public void checkAndNotifyEntretien() ;

    }
