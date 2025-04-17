package com.izorai.pfa.module2.DTO.report;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class VoyageInfo {
    private Long id;
    private String route;
    private LocalDate departureDate;
    private String camion;
    private String chaufeurFullName;
}
