package com.izorai.pfa.module1.services.camion.typeCamion;

import com.izorai.pfa.module1.DTO.camion.typeCamion.TypeCamionDTO;
import com.izorai.pfa.module1.entities.camion.TypeCamion;
import com.izorai.pfa.module1.mappers.camion.TypeCamionMapper;
import com.izorai.pfa.module1.repository.camion.TypeCamionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TypeCamionServiceImpl implements TypeCamionService {

    private final TypeCamionMapper typeCamionMapper;
    private final TypeCamionRepository typeCamionRepository;


    @Override
    public TypeCamionDTO addNewTypeCamion(TypeCamionDTO typeCamionDTO) {
        TypeCamion typeCamion = typeCamionMapper.fromTypeCamionDTO(typeCamionDTO); // Convert DTO to Entity
        typeCamionRepository.save(typeCamion);
        return typeCamionMapper.toTypeCamionDTO(typeCamion); // Return DTO after saving
    }

    @Override
    public List<TypeCamionDTO> getAllTypeCamions() {
        return typeCamionRepository.findAll().stream()
                .map(typeCamionMapper::toTypeCamionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TypeCamionDTO> getTypeCamionById(Long id) {
        return typeCamionRepository.findById(id)
                .map(typeCamionMapper::toTypeCamionDTO);
    }

    @Override
    public TypeCamionDTO updateTypeCamion(Long id, TypeCamionDTO typeCamionDTO) {
        TypeCamion updatedTypeCamion = typeCamionRepository.findById(id).map(typeCamion -> {
            typeCamion.setType(typeCamionDTO.type()); // Update the type field
            return typeCamionRepository.save(typeCamion);
        }).orElseThrow(() -> new RuntimeException("TypeCamion non trouvé"));

        return typeCamionMapper.toTypeCamionDTO(updatedTypeCamion);
    }

    @Override
    public void deleteTypeCamion(Long id) {
        typeCamionRepository.deleteById(id);
    }
}