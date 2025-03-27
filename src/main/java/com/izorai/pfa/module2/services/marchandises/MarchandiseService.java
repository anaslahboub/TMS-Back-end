package com.izorai.pfa.module2.services.marchandises;

import com.izorai.pfa.module2.DTO.marchandises.MarchandiseDTO;
import java.util.List;
import java.util.Optional;

public interface MarchandiseService {
    MarchandiseDTO createMarchandise(MarchandiseDTO marchandiseDTO);
    List<MarchandiseDTO> getAllMarchandises();
    Optional<MarchandiseDTO> getMarchandiseById(Long id);
    Optional<MarchandiseDTO> getMarchandiseByCode(String code);
    MarchandiseDTO updateMarchandise(Long id, MarchandiseDTO marchandiseDTO);
    void deleteMarchandise(Long id);
    boolean existsByCodeMarchandise(String code);
}