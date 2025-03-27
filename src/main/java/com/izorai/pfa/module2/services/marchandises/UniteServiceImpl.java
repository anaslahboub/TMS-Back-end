package com.izorai.pfa.module2.services.marchandises;

import com.izorai.pfa.module2.DTO.marchandises.UniteDTO;
import com.izorai.pfa.module2.entities.marchandises.Unite;
import com.izorai.pfa.module2.mappers.Marchandises.UniteMapper;
import com.izorai.pfa.module2.repository.marchandises.UniteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UniteServiceImpl implements UniteService {

    private final UniteRepository uniteRepository;
    private final UniteMapper uniteMapper;

    public UniteServiceImpl(UniteRepository uniteRepository,
                            UniteMapper uniteMapper) {
        this.uniteRepository = uniteRepository;
        this.uniteMapper = uniteMapper;
    }

    @Override
    public UniteDTO createUnite(UniteDTO uniteDTO) {
        Unite unite = uniteMapper.toEntity(uniteDTO);
        Unite saved = uniteRepository.save(unite);
        return uniteMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UniteDTO> getAllUnites() {
        return uniteRepository.findAll().stream()
                .map(uniteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UniteDTO> getUniteById(Long id) {
        return uniteRepository.findById(id)
                .map(uniteMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UniteDTO> getUniteByNom(String nom) {
        return uniteRepository.findByUnite(nom)
                .map(uniteMapper::toDto);
    }

    @Override
    public UniteDTO updateUnite(Long id, UniteDTO uniteDTO) {
        return uniteRepository.findById(id)
                .map(existing -> {
                    Unite updated = uniteMapper.toEntity(uniteDTO);
                    updated.setId(id);
                    Unite saved = uniteRepository.save(updated);
                    return uniteMapper.toDto(saved);
                })
                .orElseThrow(() -> new RuntimeException("Unite not found with id: " + id));
    }

    @Override
    public void deleteUnite(Long id) {
        uniteRepository.deleteById(id);
    }

    @Override
    public boolean existsByNom(String nom) {
        return uniteRepository.existsByUnite(nom);
    }
}