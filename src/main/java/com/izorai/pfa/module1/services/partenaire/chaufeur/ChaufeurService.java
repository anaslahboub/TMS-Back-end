package com.izorai.pfa.module1.services.partenaire.chaufeur;

import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurUpdateDto;
import com.izorai.pfa.module1.entities.camion.Chaufeur;

import java.util.List;

public interface ChaufeurService {

    /// CHAUFEUR SERVICE

    ChaufeurRespDTO addNewChaufeur(ChaufeurCreateDTO chaufeur);
    List<ChaufeurRespDTO> getAllChaufeurs();
    ChaufeurRespDTO getChaufeurById(Long idPartenaire);
    ChaufeurRespDTO updateChaufeur(Long id, ChaufeurUpdateDto chaufeurDetails);
    void deleteChaufeur(Long id);

    // Opérations spécifiques
    List<Chaufeur> getChaufeursDisponibles();
    void setDisponibilite(Long idPartenaire, String disponibilite);

    // Assignation
    void assignerChaufeurACamion(Long idPartenaire, String immatriculationCamion);
    void desassignerChaufeur(Long idPartenaire);

    // Statistiques
    int getNombreChaufeursActifs();
    int getNombreChaufeursEnMission();

}
