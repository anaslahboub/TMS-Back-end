package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.paretenaire.PartenaireRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.typePartenaire.TypePartenaireCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.typePartenaire.TypePartenaireRespDTO;
import com.izorai.pfa.module1.entities.camion.Chaufeur;
import com.izorai.pfa.module1.entities.partenaire.*;
import com.izorai.pfa.module1.DTO.paretenaire.paretenaire.PartenaireCreateDTO;
import com.izorai.pfa.module1.mappers.partenaire.*;
import com.izorai.pfa.module1.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class PartenaireServiceImpl implements PartenaireService {

    private final PartenaireRepository partenaireRepository;
    private final PhysiqueRepository physiqueRepository;
    private final MoraleRepository moraleRepository;
    private final ChaufeurRepository chaufeurRepository;
    private final TypePartenaireRepository typePartenaireRepository;
    private final AdressRepository adressRepository;
    private final PartenaireMapper partenaireMapper;
    private final MoraleMapper moraleMapper;
    private final PhysiqueMapper physiqueMapper;
    private final ChaufeurMapper chaufeurMapper;
    private final TypePartenaireMapper typePartenaireMapper;

    public PartenaireServiceImpl(PartenaireRepository partenaireRepository,
                                 PhysiqueRepository physiqueRepository,
                                 MoraleRepository moraleRepository,
                                 ChaufeurRepository chaufeurRepository,
                                 TypePartenaireRepository typePartenaireRepository,
                                 AdressRepository adressRepository,
                                 PartenaireMapper partenaireMapper,
                                 MoraleMapper moraleMapper,
                                 PhysiqueMapper physiqueMapper,
                                 ChaufeurMapper chaufeurMapper,
                                 TypePartenaireMapper typePartenaireMapper) {
        this.partenaireRepository = partenaireRepository;
        this.physiqueRepository = physiqueRepository;
        this.moraleRepository = moraleRepository;
        this.chaufeurRepository = chaufeurRepository;
        this.typePartenaireRepository = typePartenaireRepository;
        this.adressRepository = adressRepository;
        this.partenaireMapper = partenaireMapper;
        this.moraleMapper = moraleMapper;
        this.physiqueMapper = physiqueMapper;
        this.chaufeurMapper = chaufeurMapper;
        this.typePartenaireMapper = typePartenaireMapper;
    }

    @Override
    public PartenaireCreateDTO createPartenaire(PartenaireCreateDTO partenaireCreateDTO) {
        Partenaire partenaire = partenaireRepository.save(partenaireMapper.fromPartenaireCreateDTO(partenaireCreateDTO));
        return partenaireMapper.toPartenaireCreateDTO(partenaire);
    }



    @Override
    public List<PartenaireRespDTO> getAllPartenaires() {
        return partenaireRepository.findAll().
                stream().map(partenaireMapper::toPartenaireRespDTO).collect(Collectors.toList());
    }

    @Override
    public PartenaireRespDTO getPartenaireById(Long idPartenaire) {
        Partenaire partenaire = partenaireRepository.findById(idPartenaire)
                .orElseThrow(() -> new RuntimeException("Partenaire with id " + idPartenaire + " not found"));

        return partenaireMapper.toPartenaireRespDTO(partenaire);
    }

    @Override
    public Partenaire updatePartenaire(Partenaire partenaireDetails) {
        Partenaire partenaire = partenaireRepository.findById(partenaireDetails.getIdPartenaire()).get();
        partenaire.setNom(partenaireDetails.getNom());
        partenaire.setEmail(partenaireDetails.getEmail());
        partenaire.setTelephone(partenaireDetails.getTelephone());
        partenaire.setAdresses(partenaireDetails.getAdresses());
        partenaire.setTypePartenaires(partenaireDetails.getTypePartenaires());
        return partenaire;
    }

    @Override
    public void deletePartenaire(Long idPartenaire) {
        if (!partenaireRepository.existsById(idPartenaire)) {
            throw new EntityNotFoundException("Partenaire with id " + idPartenaire + " not found");
        }
        partenaireRepository.deleteById(idPartenaire);
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

    @Override
    @Transactional
    public ChaufeurRespDTO addNewChaufeur(ChaufeurCreateDTO chauffeurCreateDTO) {
        Chaufeur chauffeur = chaufeurMapper.fromChaufeurCreateDTO(chauffeurCreateDTO);
        chauffeur = chaufeurRepository.save(chauffeur);
        return chaufeurMapper.toChaufeurRespDTO(chauffeur);
    }

    @Override
    public List<ChaufeurRespDTO> getAllChaufeurs() {
        List<Chaufeur> chauffeurs = chaufeurRepository.findAll();
        return chauffeurs.stream()
                .map(chaufeurMapper::toChaufeurRespDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChaufeurRespDTO getChaufeurById(Long idChauffeur) {
        Chaufeur chauffeur = chaufeurRepository.findById(idChauffeur)
                .orElseThrow(() -> new RuntimeException("Chauffeur not found"));
        return chaufeurMapper.toChaufeurRespDTO(chauffeur);
    }

    @Override
    @Transactional
    public ChaufeurRespDTO updateChaufeur(Long id, ChaufeurCreateDTO chauffeurCreateDTO) {
        Chaufeur chauffeur = chaufeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chauffeur not found"));
        chauffeur.setNom(chauffeurCreateDTO.nom());
        chauffeur.setPrenom(chauffeurCreateDTO.prenom());
        chauffeur.setCNI(chauffeurCreateDTO.CNI());
        chauffeur.setCnss(chauffeurCreateDTO.cnss());
        chauffeur.setDisponibilite(chauffeurCreateDTO.disponibilite());
        chauffeur.setEmail(chauffeurCreateDTO.email());
        chauffeur.setTelephone(chauffeurCreateDTO.telephone());
        chauffeur.setDateRecrutement(chauffeurCreateDTO.dateRecrutement());

        chauffeur = chaufeurRepository.save(chauffeur);
        return chaufeurMapper.toChaufeurRespDTO(chauffeur);
    }

    @Override
    @Transactional
    public void deleteChaufeur(Long idChauffeur) {
        chaufeurRepository.findById(idChauffeur)
                .ifPresent(chauffeur -> chaufeurRepository.delete(chauffeur));
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

    @Override
    public Adress addNewAdress(Adress adress) {
        return adressRepository.save(adress);
    }

    @Override
    public List<Adress> getAllAdresses() {

        return adressRepository.findAll();
    }

    @Override
    public Adress getAdressById(Long idAdress) {
        return
                adressRepository.findById(idAdress).orElseThrow(
                        ()->{throw new RuntimeException("Adress with id "+idAdress+" not found");});
    }

    @Override
    public Adress updateAdress(Adress adressDetails) {
        Adress adress = getAdressById(adressDetails.getIdAdress());
        adress.setPays(adressDetails.getPays());
        adress.setVille(adressDetails.getVille());
        adress.setCodePostal(adressDetails.getCodePostal());
        adress.setRue(adressDetails.getRue());
        adress.setType(adressDetails.getType());
        return adress;
    }

    @Override
    public void deleteAdress(Long idAdress) {
        adressRepository.findById(idAdress).ifPresent(adress -> adressRepository.deleteById(idAdress));
    }
}
