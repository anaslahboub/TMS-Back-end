package com.izorai.pfa.module1.services.partenaire.chaufeur;

import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurCreateDTO;
import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurPermisDto;
import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurRespDTO;
import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurUpdateDto;
import com.izorai.pfa.module1.entities.camion.Chaufeur;
import com.izorai.pfa.module1.exceptions.partenaire.chaufeur.ChaufeurNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface ChaufeurService {

    ChaufeurRespDTO addNewChaufeur(ChaufeurCreateDTO chaufeurDTO);

    List<ChaufeurRespDTO> getAllChaufeurs();

    ChaufeurRespDTO getChaufeurById(Long idPartenaire);

    ChaufeurRespDTO updateChaufeur(Long id, ChaufeurUpdateDto chaufeurDetails);

    void deleteChaufeur(Long id);

    /**
     * Retrieve all available drivers
     * @return List of available drivers
     */
    List<ChaufeurRespDTO> getChaufeursDisponibles();

    /**
     * Set driver's availability status
     * @param idPartenaire Partner ID of the driver
     * @throws ChaufeurNotFoundException if driver not found
     */
    void changeDisponibilite(Long idPartenaire);


    /**
     * @return Boolean indicating insurance validity
     * @throws ChaufeurNotFoundException if driver not found
     */

    boolean isPermisValid(Long idPartenaire);

    /**
     * Find all drivers with expired insurance
     * @return List of drivers with expired insurance
     */
    List<ChaufeurRespDTO> findDriversWithExpiredPermis();

    /**
     * Update a driver's insurance expiration date
     * @param newExpirationDate New insurance expiration date
     * @return Updated driver entity
     * @throws IllegalArgumentException if date is invalid
     */
    Chaufeur updatePermisExpirationDate(Long id, LocalDate newExpirationDate);


    /**
     * Count the number of active drivers
     * @return Total number of active drivers
     */
    int getNombreChaufeursActifs();

    /**
     * Count the number of drivers currently on mission
     * @return Total number of drivers on mission
     */
    int getNombreChaufeursEnMission();


    /**
     *retourne le permet de chaffeur
     * */
    ChaufeurPermisDto getPermisPhoto(Long idChaufeur);
}