package com.izorai.pfa.module1.DTO.partenaire.chaufeur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChaufeurPermisDto {
    Long idPartenaire;
    private byte[] photoPermisRecto;
    private byte[] photoPermisVerso;
}
