package com.izorai.pfa.module1.services.camion.typeRemorque;

import com.izorai.pfa.module1.DTO.camion.typeRemorque.TypeRemorqueDTO;

import java.util.List;
import java.util.Optional;

public interface TypeRemorqueService {
    public TypeRemorqueDTO addNewTypeRemorque(TypeRemorqueDTO TypeRemorque);
    public List<TypeRemorqueDTO> getAllTypeRemorques();
    public Optional<TypeRemorqueDTO> getTypeRemorqueById(Long id);
    public TypeRemorqueDTO updateTypeRemorque(Long id, TypeRemorqueDTO TypeRemorqueDetails);
    public void deleteTypeRemorque(Long id );
}
