package com.izorai.pfa.module1.services.camion.typeCamion;

import com.izorai.pfa.module1.DTO.camion.typeCamion.TypeCamionDTO;
import java.util.List;
import java.util.Optional;

public interface TypeCamionService {
    TypeCamionDTO addNewTypeCamion(TypeCamionDTO typeCamionDTO);
    List<TypeCamionDTO> getAllTypeCamions();
    Optional<TypeCamionDTO> getTypeCamionById(Long id);
    TypeCamionDTO updateTypeCamion(Long id, TypeCamionDTO typeCamionDTO);
    void deleteTypeCamion(Long id);
}