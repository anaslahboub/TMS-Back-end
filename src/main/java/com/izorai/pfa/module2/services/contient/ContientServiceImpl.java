package com.izorai.pfa.module2.services.contient;

import com.izorai.pfa.module2.DTO.contient.ContientDTO;
import com.izorai.pfa.module2.entities.contient.Contient;
import com.izorai.pfa.module2.exceptions.ContientNotFoundException;
import com.izorai.pfa.module2.mappers.ContientMapper;
import com.izorai.pfa.module2.repository.ContientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ContientServiceImpl implements ContientService {
    private final ContientRepository contientRepository;
    private final ContientMapper contientMapper;

    @Override
    public ContientDTO createContient(ContientDTO contientDTO) {
        Contient contient = contientMapper.toEntity(contientDTO);
        Contient savedContient = contientRepository.save(contient);
        return contientMapper.toDto(savedContient);
    }

    @Override
    public ContientDTO updateContient(Long id, ContientDTO contientDTO) {
        return contientRepository.findById(id)
                .map(existingContient -> {
                    contientMapper.updateFromDto(contientDTO, existingContient);
                    Contient updated = contientRepository.save(existingContient);
                    return contientMapper.toDto(updated);
                })
                .orElseThrow(() -> new ContientNotFoundException(id));
    }

    @Override
    public void deleteContient(Long id) {
        if (!contientRepository.existsById(id)) {
            throw new ContientNotFoundException(id);
        }
        contientRepository.deleteById(id);
    }

    @Override
    public Optional<ContientDTO> getContientById(Long id) {
        return contientRepository.findById(id)
                .map(contientMapper::toDto);
    }

    @Override
    public List<ContientDTO> getAllContients() {
        return contientRepository.findAll()
                .stream()
                .map(contientMapper::toDto)
                .collect(Collectors.toList());
    }



    @Override
    public List<ContientDTO> getContientsByMarchandise(Long marchandiseId) {
        return contientRepository.findByMarchandiseId(marchandiseId)
                .stream()
                .map(contientMapper::toDto)
                .collect(Collectors.toList());
    }
}