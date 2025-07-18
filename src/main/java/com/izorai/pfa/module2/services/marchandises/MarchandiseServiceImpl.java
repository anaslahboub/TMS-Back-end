package com.izorai.pfa.module2.services.marchandises;

import com.izorai.pfa.module2.DTO.marchandises.MarchandiseDTO;
import com.izorai.pfa.module2.entities.contient.Contient;
import com.izorai.pfa.module2.entities.marchandises.Marchandise;
import com.izorai.pfa.module2.mappers.Marchandises.MarchandiseMapper;
import com.izorai.pfa.module2.repository.ContientRepository;
import com.izorai.pfa.module2.repository.marchandises.MarchandiseRepository;
import com.izorai.pfa.module2.services.contient.ContientServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MarchandiseServiceImpl implements MarchandiseService {

    private final MarchandiseRepository marchandiseRepository;
    private final MarchandiseMapper marchandiseMapper;
    private final ContientRepository contientRepository;

    public MarchandiseServiceImpl(MarchandiseRepository marchandiseRepository,
                                  MarchandiseMapper marchandiseMapper, ContientRepository contientRepository) {
        this.marchandiseRepository = marchandiseRepository;
        this.marchandiseMapper = marchandiseMapper;
        this.contientRepository =  contientRepository;
    }

    @Override
    public MarchandiseDTO createMarchandise(MarchandiseDTO marchandiseDTO) {
        Marchandise marchandise = marchandiseMapper.toEntity(marchandiseDTO);
        Marchandise saved = marchandiseRepository.save(marchandise);
        return marchandiseMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MarchandiseDTO> getAllMarchandises() {
        return marchandiseRepository.findAll().stream()
                .map(marchandiseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MarchandiseDTO> getMarchandiseById(Long id) {
        return marchandiseRepository.findById(id)
                .map(marchandiseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MarchandiseDTO> getMarchandiseByCode(String code) {
        return marchandiseRepository.findByCodeMarchandise(code)
                .map(marchandiseMapper::toDto);
    }

    @Override
    public MarchandiseDTO updateMarchandise(Long id, MarchandiseDTO marchandiseDTO) {
        return marchandiseRepository.findById(id)
                .map(existing -> {
                    Marchandise updated = marchandiseMapper.toEntity(marchandiseDTO);
                    updated.setId(id); // Ensure ID remains the same
                    Marchandise saved = marchandiseRepository.save(updated);
                    return marchandiseMapper.toDto(saved);
                })
                .orElseThrow(() -> new RuntimeException("Marchandise not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteMarchandise(Long id) {
        // First find all Contient entities that reference this marchandise
        List<Contient> contientList = contientRepository.findByMarchandiseId(id);

        // Option 1: Set marchandise to null in all Contient entities
        for (Contient contient : contientList) {
            contient.setMarchandise(null);
            contientRepository.save(contient);
        }

        // OR Option 2: Delete all Contient entities that reference this marchandise
        // contientRepository.deleteAll(contientList);

        // Then delete the marchandise
        marchandiseRepository.deleteById(id);
    }

    @Override
    public boolean existsByCodeMarchandise(String code) {
        return marchandiseRepository.existsByCodeMarchandise(code);
    }
}