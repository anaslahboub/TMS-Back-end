package com.izorai.pfa.module1.mappers.partenaire;

import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueCreateAdressDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.entities.partenaire.Physique;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")

public interface    PhysiqueMapper {
    PhysiqueMapper INSTANCE = Mappers.getMapper(PhysiqueMapper.class);
    Physique fromPhysiqueCreateDTO(PhysiqueCreateDTO physiqueCreateDTO);
    PhysiqueCreateDTO toPhysiqueCreateDTO(Physique physique);
    Physique fromPhysiqueRespDTO(PhysiqueRespDTO physiqueRespDTO);
    @Mapping(source = "idPartenaire", target = "idPartenaire")
    PhysiqueRespDTO toPhysiqueRespDTO(Physique physique);

    PhysiqueCreateAdressDTO toPhysiqueCreateAdressDTO(Physique physique);
    Physique fromPhysiqueCreateAdressDTO(PhysiqueCreateAdressDTO physiqueCreateAdressDTO);


}
