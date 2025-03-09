package com.izorai.pfa.module1.services.partenaire.morale;

import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Morale;
import com.izorai.pfa.module1.mappers.partenaire.MoraleMapper;
import com.izorai.pfa.module1.repository.partenaire.MoraleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoraleServiceImpl implements MoraleService {
    private final MoraleRepository moraleRepository;
    private final MoraleMapper moraleMapper;

    public MoraleServiceImpl(MoraleRepository moraleRepository, MoraleMapper moraleMapper) {
        this.moraleRepository = moraleRepository;
        this.moraleMapper = moraleMapper;
    }

    @Override
    public MoraleRespDTO addNewMorale(MoraleCreateDTO moraleCreateDTO) {
        Morale morale = moraleMapper.fromMoraleCreateDTO(moraleCreateDTO);
        Morale savedMorale = moraleRepository.save(morale);
        return moraleMapper.toMoraleRespDTO(savedMorale);
    }

    @Override
    public List<MoraleRespDTO> getAllMorales() {
        // Récupère toutes les entités et les mappe en DTO
        return moraleRepository.findAll().stream()
                .map(moraleMapper::toMoraleRespDTO)
                .collect(Collectors.toList());
    }


    @Override
    public MoraleRespDTO getMoraleById(Long idPartenaire) {
        Morale morale = moraleRepository.findById(idPartenaire).orElseThrow( ()->{
            throw new RuntimeException("La personne Morale with id "+idPartenaire+" not found");}
        );
        return moraleMapper.toMoraleRespDTO(morale);
    }

    @Override
    public MoraleRespDTO updateMorale(Long id,MoraleCreateDTO moraleDetails) {
        Morale morale = moraleRepository.findById(id).orElseThrow(() ->
                new RuntimeException("La personne Morale avec l'id " + id + " n'a pas été trouvée")
        );
        morale.setICE(moraleDetails.ICE());
        morale.setNom(moraleDetails.nom());
        morale.setAbreviation(moraleDetails.abreviation());
        morale.setNumeroRC(moraleDetails.numeroRC());
        morale.setFormeJuridique(moraleDetails.formeJuridique());
        morale.setEmail(moraleDetails.email());
        morale.setTelephone(moraleDetails.telephone());
        Morale updatedMorale = moraleRepository.save(morale);

        return moraleMapper.toMoraleRespDTO(updatedMorale);
    }

    @Override
    public void deleteMorale(Long idPartenaire) {
        moraleRepository.findById(idPartenaire).ifPresent(morale -> moraleRepository.deleteById(idPartenaire));
    }

}
