package com.izorai.pfa.module2.services;

import com.izorai.pfa.module2.DTO.voyage.VoyageDTO;
import com.izorai.pfa.module2.entities.Voyage;
import com.izorai.pfa.module2.mappers.VoyageMapper;
import com.izorai.pfa.module2.repository.VoyageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VoyageServiceImpl implements VoyageService {

    private final VoyageRepository voyageRepository;
    private final VoyageMapper voyageMapper;

    public VoyageServiceImpl(VoyageRepository voyageRepository,
                             VoyageMapper voyageMapper) {
        this.voyageRepository = voyageRepository;
        this.voyageMapper = voyageMapper;
    }

    @Override
    public VoyageDTO createVoyage(VoyageDTO voyageDTO) {
        Voyage voyage = voyageMapper.toEntity(voyageDTO);
        Voyage saved = voyageRepository.save(voyage);
        return voyageMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoyageDTO> getAllVoyages() {
        return voyageRepository.findAll().stream()
                .map(voyageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VoyageDTO> getVoyageById(Long id) {
        return voyageRepository.findById(id)
                .map(voyageMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public List<VoyageDTO> getVoyagesByDateRange(LocalDateTime start, LocalDateTime end) {
        return voyageRepository.findByDateDepartBetween(start, end).stream()
                .map(voyageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoyageDTO> getVoyagesByStatut(String statut) {
        return voyageRepository.findByEtat(statut).stream()
                .map(voyageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VoyageDTO updateVoyage(Long id, VoyageDTO voyageDTO) {
        return voyageRepository.findById(id)
                .map(existing -> {
                    Voyage updated = voyageMapper.toEntity(voyageDTO);
                    updated.setId(id);
                    Voyage saved = voyageRepository.save(updated);
                    return voyageMapper.toDto(saved);
                })
                .orElseThrow(() -> new RuntimeException("Voyage not found with id: " + id));
    }

    @Override
    public void deleteVoyage(Long id) {
        voyageRepository.deleteById(id);
    }


}