package com.izorai.pfa.module1.services.partenaire.typepartenaire;

import com.izorai.pfa.module1.DTO.partenaire.typePartenaire.TypePartenaireCreateDTO;
import com.izorai.pfa.module1.DTO.partenaire.typePartenaire.TypePartenaireNomDto;
import com.izorai.pfa.module1.DTO.partenaire.typePartenaire.TypePartenaireRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Morale;
import com.izorai.pfa.module1.entities.partenaire.Physique;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;
import com.izorai.pfa.module1.mappers.partenaire.TypePartenaireMapper;
import com.izorai.pfa.module1.repository.partenaire.MoraleRepository;
import com.izorai.pfa.module1.repository.partenaire.PhysiqueRepository;
import com.izorai.pfa.module1.repository.partenaire.TypePartenaireRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypePartenaireServiceImpl implements TypePartenaireService {
    private final TypePartenaireRepository typePartenaireRepository;
    private final TypePartenaireMapper typePartenaireMapper;
    private final MoraleRepository MoraleRepository;
    private final PhysiqueRepository PhysiqueRepository;
    private final PhysiqueRepository physiqueRepository;
    private final MoraleRepository moraleRepository;


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
        // Vérifier si le TypePartenaire existe
        TypePartenaire existingTypePartenaire = typePartenaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TypePartenaire avec l'ID " + id + " non trouvé"));

        existingTypePartenaire.setGenre(typePartenaireCreateDTO.genre());
        existingTypePartenaire.setDefinition(typePartenaireCreateDTO.definition());
        existingTypePartenaire.setLibelle(typePartenaireCreateDTO.libelle());
        typePartenaireRepository.save(existingTypePartenaire);
        return typePartenaireMapper.toTypePartenaireRespDTO(existingTypePartenaire);
    }

    @Override
    @Transactional
    public void deleteTypePartenaire(Long id) {
        TypePartenaire typePartenaire = typePartenaireRepository.findById(id).get();
        List<Physique> physiques = physiqueRepository.findAllByTypePartenaire(typePartenaire);
        List<Morale> morales = moraleRepository.findAllByTypePartenaire(typePartenaire);
        for(Physique physique : physiques){
            physique.setTypePartenaire(null);
        }
        for ( Morale morale : morales){
            morale.setTypePartenaire(null);
        }
        typePartenaireRepository.delete(typePartenaire);
    }

    @Override
    public List<TypePartenaireNomDto> getAllTypePartenaireNoms() {
        return typePartenaireRepository.findAll().stream().map(typePartenaireMapper::fromEntityTypePartenaire).collect(Collectors.toList());
    }

    @Override
    public TypePartenaire getPartenaireByLibelle(String nom) {
        return typePartenaireRepository.findByLibelle(nom);
    }
}
