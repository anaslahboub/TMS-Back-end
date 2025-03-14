package com.izorai.pfa.module1.services.camion.entretien;

import com.izorai.pfa.module1.DTO.camion.entretien.EntretienDTO;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Entretien;
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



    @Override
    public EntretienDTO createEntretien(EntretienDTO entretienDTO) {
        Entretien entretien = entretienMapper.fromEntretienDTO(entretienDTO); // Convertit le DTO en entité
        entretienRepository.save(entretien);
        return entretienMapper.toEntretienDTO(entretien); // Retourne le DTO après la sauvegarde
    }

    @Override
    public List<EntretienDTO> getAllEntretiens() {
        return entretienRepository.findAll().stream()
                .map(entretienMapper::toEntretienDTO)
                .collect(Collectors.toList()); // Retourne tous les entretiens
    }

    @Override
    public Optional<EntretienDTO> getEntretienById(Long id) {
        return entretienRepository.findById(id).map(entretienMapper::toEntretienDTO); // Retourne l'entretien par son ID
    }

    @Override
    public EntretienDTO updateEntretien(Long id, EntretienDTO entretienDTO) {
        Entretien updatedEntretien = entretienRepository.findById(id).map(entretien -> {
            entretien.setDateEntretien(entretienDTO.dateEntretien());
            entretien.setTypeEntretien(entretienDTO.typeEntretien());
            entretien.setDescription(entretienDTO.description());
            entretien.setCout(entretienDTO.cout());
            entretien.setDateProchainEntretien(entretienDTO.dateProchainEntretien());
            return entretienRepository.save(entretien);
        }).orElseThrow(() -> new RuntimeException("Entretien non trouvé"));

        return entretienMapper.toEntretienDTO(updatedEntretien);
    }


    @Override
    public void deleteEntretien(Long id) {
        entretienRepository.deleteById(id);
    }

    @Override
    public List<EntretienDTO> getEntretiensByCamion(String immatriculationCamion) {
        Camion camion = camionRepository.findByImmatriculation(immatriculationCamion).get();

        return camion.getEntretiens().stream().map(entretienMapper::toEntretienDTO).collect(Collectors.toList());
    }

    @Override
    public List<EntretienDTO> getEntretiensByDateRange(LocalDate debut, LocalDate fin) {
        List<Entretien> entretiens = entretienRepository.findByDateEntretienBetween(debut, fin);
        return entretiens.stream().map(entretienMapper::toEntretienDTO).collect(Collectors.toList());
    }

    @Override
    public List<EntretienDTO> getEntretiensByType(String typeEntretien) {
        List<Entretien> entretiens = entretienRepository.findByTypeEntretien(typeEntretien);
        return entretiens.stream().map(entretienMapper::toEntretienDTO).collect(Collectors.toList());
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
