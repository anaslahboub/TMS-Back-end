package com.izorai.pfa.module1.services.partenaire.chaufeur;

import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurRespDTO;
import com.izorai.pfa.module1.entities.camion.Chaufeur;

import java.util.List;

public interface ChaufeurService {

    /// CHAUFEUR SERVICE

    ChaufeurRespDTO addNewChaufeur(ChaufeurCreateDTO chaufeur);
    List<ChaufeurRespDTO> getAllChaufeurs();
    ChaufeurRespDTO getChaufeurById(Long id);
    ChaufeurRespDTO updateChaufeur( Long id,ChaufeurCreateDTO chaufeurDetails);
    void deleteChaufeur(Long id);

    // Opérations spécifiques
    List<Chaufeur> getChaufeursDisponibles();
    void setDisponibilite(Long id, String disponibilite);

    // Assignation
    void assignerChaufeurACamion(Long idChaufeur, String immatriculationCamion);
    void desassignerChaufeur(Long idChaufeur);

    // Statistiques
    int getNombreChaufeursActifs();
    int getNombreChaufeursEnMission();

}
