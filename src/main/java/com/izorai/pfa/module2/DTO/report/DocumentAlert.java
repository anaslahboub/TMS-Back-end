package com.izorai.pfa.module2.DTO.report;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public  class DocumentAlert {
    private String documentType; // "Assurance", "Carte Grise", etc.
    private LocalDate expiryDate;
    private int daysUntilExpiry;
}