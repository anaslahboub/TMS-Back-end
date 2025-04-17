package com.izorai.pfa.module2.enumerations;

public enum EtatVoyage {
    PLANIFIE,          // Le voyage est en phase de planification
    CONFIRME,          // Le voyage a été confirmé
    EN_COURS,          // Le voyage est actuellement en cours
    EN_ATTENTE,        // Le voyage est en attente (pour diverses raisons)
    EN_RETARD,         // Le voyage connaît un retard
    INTERROMPU,        // Le voyage a été interrompu temporairement
    TERMINE,           // Le voyage s'est terminé avec succès
    ANNULE,            // Le voyage a été annulé avant le départ
    EN_INCIDENT,       // Un incident a été signalé pendant le voyage
    EN_REPORT,         // Le voyage a été reporté à une date ultérieure
    EN_LIVRAISON,      // Phase spécifique de livraison en cours
    LIVRE,             // La marchandise a été livrée avec succès
    EN_RETOUR,         // Le véhicule est en retour vers la base
    CLOTURE,           // Le voyage est clôturé (toutes les formalités terminées)
    EN_CONTROLE,       // Le voyage est soumis à un contrôle
    EN_PANNE,          // Le véhicule est en panne pendant le voyage
    EN_ACCIDENT,       // Un accident est survenu pendant le voyage
    EN_DOUANE,         // Le voyage est en attente de dédouanement
    EN_REPARATION;     // Le véhicule est en réparation pendant le voyage

    public boolean estActif() {
        return this == EN_COURS || this == EN_LIVRAISON || this == EN_RETOUR
                || this == EN_INCIDENT || this == EN_PANNE || this == EN_ACCIDENT;
    }

    public boolean estTermine() {
        return this == TERMINE || this == LIVRE || this == CLOTURE
                || this == ANNULE || this == INTERROMPU;
    }
}