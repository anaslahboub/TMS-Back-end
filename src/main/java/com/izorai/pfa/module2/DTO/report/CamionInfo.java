package com.izorai.pfa.module2.DTO.report;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public  class CamionInfo {
    private String immatriculation;
    private String type;
    private String status;

}
