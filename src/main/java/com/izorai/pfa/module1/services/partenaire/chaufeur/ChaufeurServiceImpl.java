package com.izorai.pfa.module1.services.partenaire.chaufeur;

import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurUpdateDto;
import com.izorai.pfa.module1.entities.camion.Chaufeur;
import com.izorai.pfa.module1.mappers.partenaire.ChaufeurMapper;
import com.izorai.pfa.module1.repository.partenaire.ChaufeurRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChaufeurServiceImpl implements ChaufeurService {
    private final ChaufeurMapper chaufeurMapper;
    private final ChaufeurRepository chaufeurRepository;




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
    public ChaufeurRespDTO updateChaufeur(Long idPartenaire, ChaufeurUpdateDto chauffeurCreateDTO) {
        Chaufeur chauffeur = chaufeurRepository.findById(idPartenaire)
                .orElseThrow(() -> new RuntimeException("Chauffeur not found"));
        chauffeur.setNom(chauffeurCreateDTO.getNom());
        chauffeur.setEmail(chauffeurCreateDTO.getEmail());
        chauffeur.setTelephone(chauffeurCreateDTO.getTelephone());
        chauffeur.setPrenom(chauffeurCreateDTO.getPrenom());
        chauffeur.setCni(chauffeurCreateDTO.getCNI());
        chauffeur.setCnss(chauffeurCreateDTO.getCnss());
        chauffeur.setDisponibilite(chauffeurCreateDTO.getDisponibilite());
        chauffeur.setDateRecrutement(chauffeurCreateDTO.getDateRecrutement());
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
