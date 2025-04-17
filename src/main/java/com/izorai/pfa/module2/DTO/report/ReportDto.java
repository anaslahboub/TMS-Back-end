package com.izorai.pfa.module2.DTO.report;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@RequiredArgsConstructor
public class ReportDto {
    private List<VoyageInfo> voyagesPlanifies = new ArrayList<>();
    private int totalUpcomingVoyages;
    private LocalDate startDate;
    private List<DriverInfo> activeDrivers = new ArrayList<>();
    private List<CamionInfo> camionsActive = new ArrayList<>();
    private List<DocumentAlert> documentsExpirants = new ArrayList<>();
}