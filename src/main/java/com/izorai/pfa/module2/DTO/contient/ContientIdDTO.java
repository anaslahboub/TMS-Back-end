package com.izorai.pfa.module2.DTO.contient;

import com.izorai.pfa.module2.DTO.marchandises.EmballageDTO;
import com.izorai.pfa.module2.DTO.marchandises.MarchandiseDTO;
import com.izorai.pfa.module2.DTO.marchandises.UniteDTO;
import com.izorai.pfa.module2.DTO.voyage.VoyageDTO;

public record ContientIdDTO(
        VoyageDTO voyage,
        UniteDTO unite,
        MarchandiseDTO marchandise,
        EmballageDTO emballage
) {}
