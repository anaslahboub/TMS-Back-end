package com.izorai.pfa.module2.DTO.report;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public  class DriverInfo {
    private Long id;
    private String name;
    private String licenseNumber;
    private LocalDate licenseExpiry;
}