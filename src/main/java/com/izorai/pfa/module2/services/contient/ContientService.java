package com.izorai.pfa.module2.services.contient;

import com.izorai.pfa.module2.DTO.contient.ContientDTO;
import java.util.List;
import java.util.Optional;

public interface ContientService {
    // CRUD Operations
    ContientDTO createContient(ContientDTO contientDTO);
    ContientDTO updateContient(Long id, ContientDTO contientDTO);
    void deleteContient(Long id);
    Optional<ContientDTO> getContientById(Long id);
    List<ContientDTO> getAllContients();

    // Relationship Management
    List<ContientDTO> getContientsByMarchandise(Long marchandiseId);
}