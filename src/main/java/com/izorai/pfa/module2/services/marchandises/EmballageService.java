package com.izorai.pfa.module2.services.marchandises;

import com.izorai.pfa.module2.DTO.marchandises.EmballageDTO;
import java.util.List;
import java.util.Optional;

public interface EmballageService {
    EmballageDTO createEmballage(EmballageDTO emballageDTO);
    List<EmballageDTO> getAllEmballages();
    Optional<EmballageDTO> getEmballageById(Long id);
    Optional<EmballageDTO> getEmballageByNom(String nom);
    EmballageDTO updateEmballage(Long id, EmballageDTO emballageDTO);
    void deleteEmballage(Long id);
    boolean existsByNom(String nom);
}