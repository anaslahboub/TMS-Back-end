package com.izorai.pfa.module1.services.partenaire.physique;

import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Physique;
import com.izorai.pfa.module1.mappers.partenaire.PhysiqueMapper;
import com.izorai.pfa.module1.repository.partenaire.PhysiqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhysiqueServiceImpl implements PhysiqueService {
    private final PhysiqueMapper physiqueMapper;
    private final PhysiqueRepository physiqueRepository;


    public PhysiqueServiceImpl(PhysiqueMapper physiqueMapper, PhysiqueRepository physiqueRepository) {
        this.physiqueMapper = physiqueMapper;
        this.physiqueRepository = physiqueRepository;
    }


    @Override
    @Transactional
    public PhysiqueRespDTO addNewPhysique(PhysiqueCreateDTO physiqueCreateDTO) {
        // Mapping du DTO vers l'entité Physique
        Physique physique = physiqueMapper.fromPhysiqueCreateDTO(physiqueCreateDTO);
        physique = physiqueRepository.save(physique);  // Sauvegarde dans la base
        return physiqueMapper.toPhysiqueRespDTO(physique);  // Retour du DTO avec l'ID généré
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
        physique.setCNI(physiqueDetails.CNI());
        physique.setNom(physiqueDetails.nom());
        physique.setPrenom(physiqueDetails.prenom());
        physique.setTelephone(physiqueDetails.telephone());
        physique.setEmail(physiqueDetails.email());

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

}
