package com.izorai.pfa.module1.services.camion.typeCarburant;

import com.izorai.pfa.module1.DTO.camion.typeCarburant.TypeCarburantDTO;
import com.izorai.pfa.module1.entities.camion.TypeCarburant;
import com.izorai.pfa.module1.mappers.camion.TypeCarburantMapper;
import com.izorai.pfa.module1.repository.camion.TypeCarburantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypeCarburantServiceImpl implements TypeCarburantService {

    private final TypeCarburantMapper typeCarburantMapper;
    private final TypeCarburantRepository typeCarburantRepository;

    public TypeCarburantServiceImpl(TypeCarburantMapper typeCarburantMapper, TypeCarburantRepository typeCarburantRepository) {
        this.typeCarburantMapper = typeCarburantMapper;
        this.typeCarburantRepository = typeCarburantRepository;
    }

    @Override
    public TypeCarburantDTO addNewTypeCarburant(TypeCarburantDTO typeCarburantDTO) {
        TypeCarburant typeCarburant = typeCarburantMapper.fromTypeCarburantDTO(typeCarburantDTO); // Convert DTO to Entity
        typeCarburantRepository.save(typeCarburant);
        return typeCarburantMapper.toTypeCarburantDTO(typeCarburant); // Return DTO after saving
    }

    @Override
    public List<TypeCarburantDTO> getAllTypeCarburants() {
        return typeCarburantRepository.findAll().stream()
                .map(typeCarburantMapper::toTypeCarburantDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TypeCarburantDTO> getTypeCarburantById(Long id) {
        return typeCarburantRepository.findById(id)
                .map(typeCarburantMapper::toTypeCarburantDTO);
    }

    @Override
    public TypeCarburantDTO updateTypeCarburant(Long id, TypeCarburantDTO typeCarburantDTO) {
        TypeCarburant updatedTypeCarburant = typeCarburantRepository.findById(id).map(typeCarburant -> {
            typeCarburant.setType(typeCarburantDTO.type()); // Update the type field
            return typeCarburantRepository.save(typeCarburant);
        }).orElseThrow(() -> new RuntimeException("TypeCarburant non trouvé"));

        return typeCarburantMapper.toTypeCarburantDTO(updatedTypeCarburant);
    }

    @Override
    public void deleteTypeCarburant(Long id) {
        typeCarburantRepository.deleteById(id);
    }

}