package com.izorai.pfa.module2.services.Emballage;

import com.izorai.pfa.module2.DTO.marchandises.EmballageDTO;
import com.izorai.pfa.module2.entities.marchandises.Emballage;
import com.izorai.pfa.module2.mappers.Marchandises.EmballageMapper;
import com.izorai.pfa.module2.repository.marchandises.EmballageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmballageServiceImpl implements EmballageService {

    private final EmballageRepository emballageRepository;
    private final EmballageMapper emballageMapper;

    public EmballageServiceImpl(EmballageRepository emballageRepository,
                                EmballageMapper emballageMapper) {
        this.emballageRepository = emballageRepository;
        this.emballageMapper = emballageMapper;
    }

    @Override
    public EmballageDTO createEmballage(EmballageDTO emballageDTO) {
        Emballage emballage = emballageMapper.toEntity(emballageDTO);
        Emballage saved = emballageRepository.save(emballage);
        return emballageMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmballageDTO> getAllEmballages() {
        return emballageRepository.findAll().stream()
                .map(emballageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmballageDTO> getEmballageById(Long id) {
        return emballageRepository.findById(id)
                .map(emballageMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmballageDTO> getEmballageByNom(String nom) {
        return emballageRepository.findByNom(nom)
                .map(emballageMapper::toDto);
    }

    @Override
    public EmballageDTO updateEmballage(Long id, EmballageDTO emballageDTO) {
        return emballageRepository.findById(id)
                .map(existing -> {
                    Emballage updated = emballageMapper.toEntity(emballageDTO);
                    updated.setId(id);
                    Emballage saved = emballageRepository.save(updated);
                    return emballageMapper.toDto(saved);
                })
                .orElseThrow(() -> new RuntimeException("Emballage not found with id: " + id));
    }

    @Override
    public void deleteEmballage(Long id) {
        emballageRepository.deleteById(id);
    }

    @Override
    public boolean existsByNom(String nom) {
        return emballageRepository.existsByNom(nom);
    }
}