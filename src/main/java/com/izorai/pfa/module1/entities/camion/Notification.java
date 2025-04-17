package com.izorai.pfa.module1.entities.camion;

import com.izorai.pfa.module1.entities.enumerations.NotificationFrom;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@Entity
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private boolean vu;
    private LocalDate dateEnvoie;
    @Enumerated(EnumType.STRING)
    private NotificationFrom notificationFrom;
}
