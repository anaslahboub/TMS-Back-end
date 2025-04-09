package com.izorai.pfa.module2.mappers;

import com.izorai.pfa.module2.DTO.contient.ContientDTO;
import com.izorai.pfa.module2.entities.contient.Contient;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ContientMapper {
    ContientDTO toDto(Contient contient);
    Contient toEntity(ContientDTO contientDTO);
    @Mapping(target = "id", ignore = true)
    void updateFromDto(ContientDTO dto, @MappingTarget Contient entity);
}