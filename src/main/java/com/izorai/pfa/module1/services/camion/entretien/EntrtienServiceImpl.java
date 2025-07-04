package com.izorai.pfa.module1.services.camion.entretien;

import com.izorai.pfa.module1.DTO.camion.camion.CamionDTO;
import com.izorai.pfa.module1.DTO.camion.entretien.EntretienDTO;
import com.izorai.pfa.module1.DTO.camion.entretien.EntretienViewResp;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Entretien;
import com.izorai.pfa.module1.mappers.camion.CamionMapper;
import com.izorai.pfa.module1.mappers.camion.EntretienMapper;
import com.izorai.pfa.module1.repository.camion.CamionRepository;
import com.izorai.pfa.module1.repository.camion.EntretienRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class EntrtienServiceImpl implements EntrtienService {


    private final EntretienRepository entretienRepository;
    private final EntretienMapper entretienMapper;
    private final CamionRepository camionRepository;
private final CamionMapper camionMapper;


    @Override
    public EntretienDTO createEntretien(EntretienDTO entretienDTO) {
        Entretien entretien = entretienMapper.fromEntretienDTO(entretienDTO); // Convertit le DTO en entité
        entretienRepository.save(entretien);
        return entretienMapper.toEntretienDTO(entretien); // Retourne le DTO après la sauvegarde
    }

    @Override
    public List<EntretienViewResp> getAllEntretiens() {
        return entretienRepository.findAll().stream()
                .map(entretienMapper::toEntretienViewResp)
                .collect(Collectors.toList()); // Retourne tous les entretiens
    }

    @Override
    public Optional<EntretienViewResp> getEntretienById(Long id) {
        return entretienRepository.findById(id).map(entretienMapper::toEntretienViewResp); // Retourne l'entretien par son ID
    }

    @Override
    public EntretienDTO updateEntretien(Long id, EntretienDTO entretienDTO) {
        Entretien updatedEntretien = entretienRepository.findById(id).map(entretien -> {
            entretien.setDateEntretien(entretienDTO.getDateEntretien());
            entretien.setTypeEntretien(entretienDTO.getTypeEntretien());
            entretien.setDescription(entretienDTO.getDescription());
            entretien.setCout(entretienDTO.getCout());
            entretien.setDateProchainEntretien(entretienDTO.getDateProchainEntretien());
            entretien.setCamion(entretienDTO.getCamion());
            return entretienRepository.save(entretien);
        }).orElseThrow(() -> new RuntimeException("Entretien non trouvé"));

        return entretienMapper.toEntretienDTO(updatedEntretien);
    }


    @Override
    public void deleteEntretien(Long id) {
        entretienRepository.deleteById(id);
    }

    @Override
    public CamionDTO getCamionByIdEntretiens(Long id) {
        Entretien entretien = entretienRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return camionMapper.toCamionDto(entretien.getCamion());

    }

    @Override
    public List<EntretienViewResp> getEntretiensByCamion(String immatriculationCamion) {
        Camion camion = camionRepository.findByImmatriculation(immatriculationCamion).get();

        return camion.getEntretiens().stream().map(entretienMapper::toEntretienViewResp).collect(Collectors.toList());
    }

    @Override
    public List<EntretienViewResp> getEntretiensByDateRange(LocalDate debut, LocalDate fin) {
        List<Entretien> entretiens = entretienRepository.findByDateEntretienBetween(debut, fin);
        return entretiens.stream().map(entretienMapper::toEntretienViewResp).collect(Collectors.toList());
    }

    @Override
    public List<EntretienViewResp> getEntretiensByType(String typeEntretien) {
        List<Entretien> entretiens = entretienRepository.findByTypeEntretien(typeEntretien);
        return entretiens.stream().map(entretienMapper::toEntretienViewResp).collect(Collectors.toList());
    }

    @Override
    public List<Entretien> getProchainsEntertiensIn30Days() {
        LocalDate today = LocalDate.now();
        LocalDate day = today.plusDays(30);
        List<Entretien> entretiens = entretienRepository.findByDateEntretienBetween(day, today);
        return entretiens;
    }

    @Override
    public List<Entretien> getProchainsEntertiensBefore30Days(){
        LocalDate today = LocalDate.now();
        LocalDate day = today.minusDays(30);
        return entretienRepository.findByDateEntretienBetween(today, day);
    }


    @Override
    public double getCoutTotalEntretiensByCamion(String immatriculationCamion) {
        List<Entretien> entretiens = entretienRepository.findByCamionImmatriculation(immatriculationCamion);

        return entretiens.stream().mapToDouble(Entretien::getCout).sum();
    }

    @Override
    public double getCoutTotalEntretiensByPeriode(LocalDate debut, LocalDate fin) {
        List<Entretien> entretiens = entretienRepository.findByDateEntretienBetween(debut, fin);
        return entretiens.stream().mapToDouble(Entretien::getCout).sum();
    }



    @Override
    public double calculateCoutEntretiens(String immatriculation) {
        // Rechercher le camion par immatriculation
        Camion camion = camionRepository.findByImmatriculation(immatriculation).get();
        if (camion == null) {
            throw new EntityNotFoundException("Camion non trouvé avec l'immatriculation " + immatriculation);
        }

        // Récupérer les entretiens effectués pour ce camion dans la période spécifiée
        List<Entretien> entretiens = camion.getEntretiens();

        // Calculer le coût total des entretiens
        double coutTotal = 0;
        for (Entretien entretien : entretiens) {
            coutTotal += entretien.getCout();
        }

        return coutTotal;
    }


}
