package com.izorai.pfa.module1.mappers.camion;

import com.izorai.pfa.module1.DTO.camion.remorque.RemorqueDTO;
import com.izorai.pfa.module1.entities.camion.Remorque;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RemorqueMapper {
    RemorqueMapper INSTANCE = Mappers.getMapper(RemorqueMapper.class);
    Remorque fromRemorqueDTO(RemorqueDTO remorqueDTO);
    RemorqueDTO toRemorqueDTO(Remorque remorque);
}
