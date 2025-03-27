    package com.izorai.pfa.module1.entities.camion;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.io.Serializable;
    import java.time.LocalDate;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Assurance implements Serializable {
        @Id
        private Long numeroContrat;
        private String company;
        private String typeCouverture;
        private int montant;
        private LocalDate dateDebut;
        private LocalDate dateExpiration;
        private int primeAnnuelle;
        private Long numCarteVerte;
        private boolean active=true;

    }
