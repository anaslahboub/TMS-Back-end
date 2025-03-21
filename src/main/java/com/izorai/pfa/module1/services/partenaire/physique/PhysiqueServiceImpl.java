package com.izorai.pfa.module1.services.partenaire.physique;

import com.izorai.pfa.module1.DTO.paretenaire.adress.AdressCreateDto;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.entities.partenaire.Physique;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;
import com.izorai.pfa.module1.mappers.partenaire.PhysiqueMapper;
import com.izorai.pfa.module1.repository.partenaire.PhysiqueRepository;
import com.izorai.pfa.module1.repository.partenaire.TypePartenaireRepository;
import com.izorai.pfa.module1.services.partenaire.adress.AdressService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PhysiqueServiceImpl implements PhysiqueService {
    private final PhysiqueMapper physiqueMapper;
    private final PhysiqueRepository physiqueRepository;
    private final AdressService adressService;
    private final TypePartenaireRepository typePartenaireRepository;


    @Override
    public PhysiqueRespDTO addNewPhysique(PhysiqueCreateDTO physiqueCreateDTO) {
        Physique physique =physiqueRepository.save( physiqueMapper.fromPhysiqueCreateDTO(physiqueCreateDTO));
        return physiqueMapper.toPhysiqueRespDTO(physique);
    }

    @Override
    public List<PhysiqueRespDTO> getAllPhysiques() {
        List<Physique> physiques = physiqueRepository.findAll();  // Récupère la liste des entités Physique
        return physiques.stream()
                .map(physiqueMapper::toPhysiqueRespDTO)  // Convertir chaque entité en DTO
                .collect(Collectors.toList());  // Retourne la liste des DTO
    }

    @Override
    public PhysiqueRespDTO getPhysiqueById(Long idPartenaire) {
        Physique physique = physiqueRepository.findById(idPartenaire).orElseThrow(
                () -> new RuntimeException("La personne Physique avec l'id " + idPartenaire + " n'a pas été trouvée")
        );
        return physiqueMapper.toPhysiqueRespDTO(physique);  // Conversion en DTO
    }

    @Override
    @Transactional
    public PhysiqueRespDTO updatePhysique(Long id, PhysiqueCreateDTO physiqueDetails) {
        // Récupère l'entité existante
        Physique physique = physiqueRepository.findById(id).orElseThrow(
                () -> new RuntimeException("La personne Physique avec l'id " + id + " n'a pas été trouvée")
        );

        // Met à jour les champs de l'entité avec les détails du DTO
        physique.setCni(physiqueDetails.getCni());
        physique.setNom(physiqueDetails.getNom());
        physique.setPrenom(physiqueDetails.getPrenom());
        physique.setTelephone(physiqueDetails.getTelephone());
        physique.setEmail(physiqueDetails.getEmail());
        physique.setTypePartenaire(physiqueDetails.getTypePartenaire());

        // Sauvegarde l'entité mise à jour dans la base de données
        Physique updatedPhysique = physiqueRepository.save(physique);

        // Retourne le DTO de l'entité mise à jour
        return physiqueMapper.toPhysiqueRespDTO(updatedPhysique);
    }

    @Override
    @Transactional
    public void deletePhysique(Long idPartenaire) {
        Physique physique = physiqueRepository.findById(idPartenaire).orElseThrow(
                () -> new RuntimeException("La personne Physique avec l'id " + idPartenaire + " n'a pas été trouvée")
        );
        physiqueRepository.delete(physique);  // Supprime l'entité Physique
    }

    @Override
    public List<Adress> getAdressesPhysique(Long idPartenaire) {
        Physique physique = physiqueRepository.findById(idPartenaire).orElseThrow();
        return physique.getAdresses();
    }

    @Override
    @Transactional
    public Adress addAdressPhysique(Long idPartenaire, AdressCreateDto adress) {
        Adress adress1 = adressService.addNewAdress(adress);

        Physique physique = physiqueRepository.findById(idPartenaire).get();
        physique.getAdresses().add(adress1);
        return adress1;
    }

}
