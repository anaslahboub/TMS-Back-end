package com.izorai.pfa.module1.services.camion.camion;

import com.izorai.pfa.module1.DTO.camion.camion.CamionDTO;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Entretien;
import com.izorai.pfa.module1.mappers.camion.CamionMapper;
import com.izorai.pfa.module1.repository.camion.CamionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Transactional
public class CamionServiceImpl implements CamionService {

    private final CamionRepository camionRepository;
    private final CamionMapper camionMapper;

    public CamionServiceImpl(CamionRepository camionRepository, CamionMapper camionMapper) {
        this.camionRepository = camionRepository;
        this.camionMapper = camionMapper;
    }

    @Override
    public CamionDTO addNewCamion(CamionDTO camionDTO) {
        // Convert DTO to entity
        Camion camion = camionMapper.fromCamionDTO(camionDTO);
        // Save the entity
        Camion savedCamion = camionRepository.save(camion);
        // Convert the saved entity back to DTO
        return camionMapper.toCamionDto(savedCamion);
    }

    @Override
    @Transactional(readOnly = true) // Optimize for read-only operations
    public List<CamionDTO> getAllCamions() {
        return camionRepository.findAll()
                .stream()
                .map(camionMapper::toCamionDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true) // Optimize for read-only operations
    public Optional<CamionDTO> getCamionById(String immatriculation) {
        return camionRepository.findByImmatriculation(immatriculation)
                .map(camionMapper::toCamionDto);
    }

    @Override
    public CamionDTO updateCamion(String immatriculation, CamionDTO camionDTO) {
        Camion updatedCamion = camionRepository.findByImmatriculation(immatriculation).map(camion -> {
            camion.setImmatriculation(camionMapper.fromCamionDTO(camionDTO).getImmatriculation());
            camion.setPoidsMax(camionMapper.fromCamionDTO(camionDTO).getPoidsMax());
            camion.setTypeCamion(camionMapper.fromCamionDTO(camionDTO).getTypeCamion());
            camion.setAssurance(camionMapper.fromCamionDTO(camionDTO).getAssurance());
            camion.setCarteGrise(camionMapper.fromCamionDTO(camionDTO).getCarteGrise());
            camion.setStatus(camionMapper.fromCamionDTO(camionDTO).getStatus());
            return camionRepository.save(camion);
        }).orElseThrow(() -> new RuntimeException("Camion non trouvé"));

        return camionMapper.toCamionDto(updatedCamion);
    }

    @Override
    public void deleteCamion(String immatriculation) {
        if (!camionRepository.existsByImmatriculation(immatriculation)) {
            throw new RuntimeException("Camion non trouvé avec l'immatriculation: " + immatriculation);
        }
        // Delete the camion
        camionRepository.deleteByImmatriculation(immatriculation);    }


    @Override
    public int getNombreCamionsActifs() {
        return 0;
    }

    @Override
    public int getNombreCamionsEnMaintenance() {
        return 0; // Comptabilise les camions ayant un entretien prévu
    }

    @Override
    public Map<String, Double> getCoutsTotauxParCamion(LocalDate debut, LocalDate fin) {
       return null;
    }

    @Override
    public Map<String, Double> getCoutsCarburantParCamion(LocalDate debut, LocalDate fin) {
        return  null;
    }

    @Override
    public Map<String, Double> getCoutsEntretienParCamion(LocalDate debut, LocalDate fin) {
        return null;
    }

    @Override
    public Map<String, Double> getConsommationMoyenneParCamion() {
       return null;
    }

    @Override
    public Map<String, Integer> getKilometragesParcourusParCamion(LocalDate debut, LocalDate fin) {
       return null;
    }


}
