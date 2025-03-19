package com.izorai.pfa.module1.mappers.camion;

import com.izorai.pfa.module1.DTO.camion.typeRemorque.TypeRemorqueDTO;
import com.izorai.pfa.module1.entities.camion.TypeRemorque;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface TypeRemorqueMapper {
    TypeRemorqueMapper INSTANCE = Mappers.getMapper(TypeRemorqueMapper.class);
    TypeRemorque toEntity(TypeRemorqueDTO TypeTypeRemorqueDTO);
    TypeRemorqueDTO fromEntity(TypeRemorque TypeRemorque);
}
