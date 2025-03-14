package com.izorai.pfa.module1.services.partenaire.chaufeur;

import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurRespDTO;
import com.izorai.pfa.module1.entities.camion.Chaufeur;
import com.izorai.pfa.module1.mappers.partenaire.ChaufeurMapper;
import com.izorai.pfa.module1.repository.partenaire.ChaufeurRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChaufeurServiceImpl implements ChaufeurService {
    private final ChaufeurMapper chaufeurMapper;
    private final ChaufeurRepository chaufeurRepository;

    public ChaufeurServiceImpl(ChaufeurMapper chaufeurMapper, ChaufeurRepository chaufeurRepository) {
        this.chaufeurMapper = chaufeurMapper;
        this.chaufeurRepository = chaufeurRepository;
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
    public ChaufeurRespDTO getChaufeurById(Long idPartenaire) {
        Chaufeur chauffeur = chaufeurRepository.findByIdPartenaire(idPartenaire);
        return chaufeurMapper.toChaufeurRespDTO(chauffeur);
    }

    @Override
    @Transactional
    public ChaufeurRespDTO updateChaufeur(Long idPartenaire, ChaufeurCreateDTO chauffeurCreateDTO) {
        Chaufeur chauffeur = chaufeurRepository.findById(idPartenaire)
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
    public void deleteChaufeur(Long idPartenaire) {
        chaufeurRepository.findById(idPartenaire)
                .ifPresent(chauffeur -> chaufeurRepository.delete(chauffeur));
    }

    @Override
    public List<Chaufeur> getChaufeursDisponibles() {
        return List.of();
    }

    @Override
    public void setDisponibilite(Long idPartenaire, String disponibilite) {

    }

    @Override
    public void assignerChaufeurACamion(Long idPartenaire, String immatriculationCamion) {

    }

    @Override
    public void desassignerChaufeur(Long idPartenaire) {

    }

    @Override
    public int getNombreChaufeursActifs() {
        return 0;
    }

    @Override
    public int getNombreChaufeursEnMission() {
        return 0;
    }

}
