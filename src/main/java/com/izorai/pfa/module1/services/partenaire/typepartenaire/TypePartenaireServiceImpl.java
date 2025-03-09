package com.izorai.pfa.module1.services.partenaire.typepartenaire;

import com.izorai.pfa.module1.DTO.paretenaire.typePartenaire.TypePartenaireCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.typePartenaire.TypePartenaireRespDTO;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;
import com.izorai.pfa.module1.mappers.partenaire.TypePartenaireMapper;
import com.izorai.pfa.module1.repository.partenaire.TypePartenaireRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypePartenaireServiceImpl implements TypePartenaireService {
    private final TypePartenaireRepository typePartenaireRepository;
    private final TypePartenaireMapper typePartenaireMapper;

    public TypePartenaireServiceImpl(TypePartenaireRepository typePartenaireRepository, TypePartenaireMapper typePartenaireMapper) {
        this.typePartenaireRepository = typePartenaireRepository;
        this.typePartenaireMapper = typePartenaireMapper;
    }


    @Override
    @Transactional
    public TypePartenaireRespDTO addNewTypePartenaire(TypePartenaireCreateDTO typePartenaireCreateDTO) {
        TypePartenaire typePartenaire = typePartenaireMapper.fromTypePartenaireCreateDTO(typePartenaireCreateDTO);
        typePartenaire = typePartenaireRepository.save(typePartenaire);
        return typePartenaireMapper.toTypePartenaireRespDTO(typePartenaire);
    }

    @Override
    public List<TypePartenaireRespDTO> getAllTypePartenaires() {
        List<TypePartenaire> typePartenaires = typePartenaireRepository.findAll();
        return typePartenaires.stream()
                .map(typePartenaireMapper::toTypePartenaireRespDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TypePartenaireRespDTO getTypePartenaireById(Long id) {
        TypePartenaire typePartenaire = typePartenaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TypePartenaire not found"));
        return typePartenaireMapper.toTypePartenaireRespDTO(typePartenaire);
    }

    @Transactional
    public TypePartenaireRespDTO updateTypePartenaire(Long id, TypePartenaireCreateDTO typePartenaireCreateDTO) {

        return null;
    }

    @Override
    @Transactional
    public void deleteTypePartenaire(Long id) {
        typePartenaireRepository.findById(id)
                .ifPresent(typePartenaire -> typePartenaireRepository.delete(typePartenaire));
    }
}
