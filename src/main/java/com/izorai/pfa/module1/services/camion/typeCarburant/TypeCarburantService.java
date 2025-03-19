package com.izorai.pfa.module1.services.camion.typeCarburant;

import com.izorai.pfa.module1.DTO.camion.typeCarburant.TypeCarburantDTO;
import com.izorai.pfa.module1.DTO.camion.typeCarburant.TypeCarburantDTO;
import com.izorai.pfa.module1.entities.camion.TypeCarburant;

import java.util.List;
import java.util.Optional;

public interface TypeCarburantService {
    public TypeCarburantDTO addNewTypeCarburant(TypeCarburantDTO TypeCarburant);
    public List<TypeCarburantDTO> getAllTypeCarburants();
    public Optional<TypeCarburantDTO> getTypeCarburantById(Long id);
    public TypeCarburantDTO updateTypeCarburant(Long id, TypeCarburantDTO TypeCarburantDetails);
    public void deleteTypeCarburant(Long id );

}
