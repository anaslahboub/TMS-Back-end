package com.izorai.pfa.module2.services.Unite;

import com.izorai.pfa.module2.DTO.marchandises.UniteDTO;
import java.util.List;
import java.util.Optional;

public interface UniteService {
    UniteDTO createUnite(UniteDTO uniteDTO);
    List<UniteDTO> getAllUnites();
    Optional<UniteDTO> getUniteById(Long id);
    Optional<UniteDTO> getUniteByNom(String nom);
    UniteDTO updateUnite(Long id, UniteDTO uniteDTO);
    void deleteUnite(Long id);
    boolean existsByNom(String nom);
}