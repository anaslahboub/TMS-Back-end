package com.izorai.pfa.module1.services.camion.typeRemorque;

import com.izorai.pfa.module1.DTO.camion.typeRemorque.TypeRemorqueDTO;
import com.izorai.pfa.module1.entities.camion.TypeRemorque;
import com.izorai.pfa.module1.mappers.camion.TypeRemorqueMapper;
import com.izorai.pfa.module1.repository.camion.TypeRemorqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TypeRemorqueServiceImpl implements TypeRemorqueService {
    private final TypeRemorqueMapper typeRemorqueMapper;
    private final TypeRemorqueRepository typeRemorqueRepository;

    

    @Override
    public TypeRemorqueDTO addNewTypeRemorque(TypeRemorqueDTO typeRemorqueDTO) {
        TypeRemorque typeRemorque = typeRemorqueMapper.toEntity(typeRemorqueDTO); // Convert DTO to Entity
        typeRemorqueRepository.save(typeRemorque);
        return typeRemorqueMapper.fromEntity(typeRemorque); // Return DTO after saving
    }

    @Override
    public List<TypeRemorqueDTO> getAllTypeRemorques() {
        return typeRemorqueRepository.findAll().stream()
                .map(typeRemorqueMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TypeRemorqueDTO> getTypeRemorqueById(Long id) {
        return typeRemorqueRepository.findById(id)
                .map(typeRemorqueMapper::fromEntity);
    }

    @Override
    public TypeRemorqueDTO updateTypeRemorque(Long id, TypeRemorqueDTO typeRemorqueDTO) {
        TypeRemorque updatedTypeRemorque = typeRemorqueRepository.findById(id).map(typeRemorque -> {
            typeRemorque.setType(typeRemorqueDTO.type()); // Update the type field
            return typeRemorqueRepository.save(typeRemorque);
        }).orElseThrow(() -> new RuntimeException("TypeRemorque non trouvé"));

        return typeRemorqueMapper.fromEntity(updatedTypeRemorque);
    }

    @Override
    public void deleteTypeRemorque(Long id) {
        typeRemorqueRepository.deleteById(id);
    }
}
