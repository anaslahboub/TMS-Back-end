package com.izorai.pfa.module1.services.partenaire.morale;

import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.entities.partenaire.Morale;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;
import com.izorai.pfa.module1.mappers.partenaire.MoraleMapper;
import com.izorai.pfa.module1.repository.partenaire.AdressRepository;
import com.izorai.pfa.module1.repository.partenaire.MoraleRepository;
import com.izorai.pfa.module1.repository.partenaire.TypePartenaireRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MoraleServiceImpl implements MoraleService {
    private final MoraleRepository moraleRepository;
    private final MoraleMapper moraleMapper;
    private final TypePartenaireRepository typePartenaireRepository;



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
    @Transactional
    public MoraleRespDTO updateMorale(Long id, MoraleCreateDTO moraleDetails) {
        // Récupérer la personne morale existante
        Morale morale = moraleRepository.findById(id).orElseThrow(() ->
                new RuntimeException("La personne Morale avec l'id " + id + " n'a pas été trouvée")
        );

        // Mettre à jour les champs
        morale.setIce(moraleDetails.getIce());
        morale.setNom(moraleDetails.getNom());
        morale.setAbreviation(moraleDetails.getAbreviation());
        morale.setNumeroRC(moraleDetails.getNumeroRC());
        morale.setFormeJuridique(moraleDetails.getFormeJuridique());
        morale.setEmail(moraleDetails.getEmail());
        morale.setTelephone(moraleDetails.getTelephone());
        morale.setTypePartenaire(moraleDetails.getTypePartenaire());

        Morale updatedMorale = moraleRepository.save(morale);

        return moraleMapper.toMoraleRespDTO(updatedMorale);
    }


    @Override
    public void deleteMorale(Long idPartenaire) {
        moraleRepository.findById(idPartenaire).ifPresent(morale -> moraleRepository.deleteById(idPartenaire));
    }

    @Override
    public List<Adress> getAdressesMorale(Long idPartenaire) {
        MoraleRespDTO moraleRespDTO=getMoraleById(idPartenaire);
        Morale morale =moraleMapper.fromMoraleRespDTO(moraleRespDTO);
        return morale.getAdresses();
    }

}
