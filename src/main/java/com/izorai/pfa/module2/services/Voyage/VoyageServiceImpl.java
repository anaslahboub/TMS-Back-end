package com.izorai.pfa.module2.services.Voyage;

import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Chaufeur;
import com.izorai.pfa.module1.entities.camion.Remorque;
import com.izorai.pfa.module1.mappers.camion.CamionMapper;
import com.izorai.pfa.module1.mappers.camion.RemorqueMapper;
import com.izorai.pfa.module1.mappers.partenaire.ChaufeurMapper;
import com.izorai.pfa.module1.repository.camion.CamionRepository;
import com.izorai.pfa.module1.repository.camion.RemorqueRepository;
import com.izorai.pfa.module1.repository.partenaire.ChaufeurRepository;
import com.izorai.pfa.module2.DTO.voyage.VoyageDTO;
import com.izorai.pfa.module2.DTO.voyage.VoyageEtatDTO;
import com.izorai.pfa.module2.entities.Voyage;
import com.izorai.pfa.module2.entities.contient.Contient;
import com.izorai.pfa.module2.enumerations.EtatVoyage;
import com.izorai.pfa.module2.exceptions.VoyageNotFoundException;
import com.izorai.pfa.module2.mappers.ContientMapper;
import com.izorai.pfa.module2.mappers.VoyageMapper;
import com.izorai.pfa.module2.repository.VoyageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class VoyageServiceImpl implements VoyageService {
    private final VoyageRepository voyageRepository;
    private final VoyageMapper voyageMapper;
    private final ChaufeurRepository chauffeurRepository;
    private final CamionRepository camionRepository;
    private final RemorqueRepository remorqueRepository;
    private final ContientMapper contientMapper;
    private final CamionMapper camionMapper;
    private final RemorqueMapper remorqueMapper;
    private final ChaufeurMapper chaufeurMapper;


    private boolean isValidTransition(EtatVoyage current, EtatVoyage next) {
        // Define all valid state transitions
        Map<EtatVoyage, Set<EtatVoyage>> validTransitions = Map.of(
                EtatVoyage.PLANIFIE, Set.of(EtatVoyage.EN_COURS, EtatVoyage.ANNULE),
                EtatVoyage.EN_COURS, Set.of(EtatVoyage.EN_INCIDENT, EtatVoyage.TERMINE),
                EtatVoyage.EN_INCIDENT, Set.of(EtatVoyage.EN_COURS, EtatVoyage.TERMINE)
        );

        // No transitions allowed from TERMINE or ANNULE
        if (current == EtatVoyage.TERMINE || current == EtatVoyage.ANNULE) {
            return false;
        }

        // Check if the requested transition is valid
        return validTransitions.getOrDefault(current, Set.of()).contains(next);
    }

    @Override
    public VoyageDTO createVoyage(VoyageDTO voyageDTO) {
        Voyage voyage = voyageMapper.toEntity(voyageDTO);
        voyage.setEtat(EtatVoyage.PLANIFIE);
        voyage.checkForWarnings();
        Voyage savedVoyage = voyageRepository.save(voyage);
        return voyageMapper.toDto(savedVoyage);
    }

    @Override
    public VoyageDTO updateVoyage(Long id, VoyageDTO voyageDTO) {
        Voyage existingVoyage = voyageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voyage with ID " + id + " not found"));

        // Update the fields
        existingVoyage.setDateDepart(voyageDTO.dateDepart());
        existingVoyage.setDateArrivePrevue(voyageDTO.dateArrivePrevue());
        existingVoyage.setLieuDepart(voyageDTO.lieuDepart());
        existingVoyage.setLieuArrive(voyageDTO.lieuArrive());
        existingVoyage.setDistance(voyageDTO.distance());
        existingVoyage.setEtat(voyageDTO.etat());
        existingVoyage.setEstUrgent(voyageDTO.estUrgent());
        existingVoyage.setEstFragile(voyageDTO.estFragile());

        // Convert DTOs to entities if necessary
        existingVoyage.setCamion(camionMapper.fromCamionDTO(voyageDTO.camion()));
        existingVoyage.setChaufeur(chaufeurMapper.fromChaufeurRespDTO(voyageDTO.chaufeur()));
        existingVoyage.setRemorque(remorqueMapper.toEntity(voyageDTO.remorque()));

        // Update the list of marchandises without replacing the collection
        existingVoyage.getListMarchandises().clear(); // Clear existing items
        voyageDTO.listMarchandises().stream()
                .map(contientMapper::toEntity)
                .forEach(existingVoyage.getListMarchandises()::add); // Add new items

        System.out.println(existingVoyage);

        // Save the updated voyage
        Voyage updatedVoyage = voyageRepository.save(existingVoyage);

        // Convert back to DTO
        return voyageMapper.toDto(updatedVoyage);
    }


    @Override
    public void deleteVoyage(Long id) {
        if (!voyageRepository.existsById(id)) {
            throw new VoyageNotFoundException(id);
        }
        voyageRepository.deleteById(id);
    }

    @Override
    public Optional<VoyageDTO> getVoyageById(Long id) {
        return voyageRepository.findById(id)
                .map(voyageMapper::toDto);
    }

    @Override
    public List<VoyageDTO> getAllVoyages() {
        return voyageRepository.findAll(Sort.by(Sort.Direction.DESC, "dateDepart"))
                .stream()
                .map(voyageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VoyageDTO updateStatus(Long id, EtatVoyage newStatus) {
        return voyageRepository.findById(id)
                .map(voyage -> {
                    if (!voyage.getWarnings().isEmpty() && newStatus.equals(EtatVoyage.EN_COURS)) {
                        return null ;
                    }else{
                        voyage.setEtat(newStatus);
                        Voyage updated = voyageRepository.save(voyage);
                        return voyageMapper.toDto(updated);
                    }

                })
                .orElseThrow(() -> new VoyageNotFoundException(id));
    }

    @Override
    public List<VoyageDTO> getVoyagesByStatus(EtatVoyage status) {
        return voyageRepository.findByEtat(status)
                .stream()
                .map(voyageMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<VoyageDTO> getVoyagesByDateRange(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return voyageRepository.findByDateDepartBetween(start, end)
                .stream()
                .map(voyageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VoyageDTO assignChauffeur(Long voyageId, Long chauffeurId) {
        Voyage voyage = voyageRepository.findById(voyageId)
                .orElseThrow(() -> new VoyageNotFoundException(voyageId));
        Chaufeur chauffeur = chauffeurRepository.findByIdPartenaire(chauffeurId);

        voyage.setChaufeur(chauffeur);
        Voyage updated = voyageRepository.save(voyage);
        return voyageMapper.toDto(updated);
    }

    @Override
    public VoyageDTO assignCamion(Long voyageId, Long camionId) {
        Voyage voyage = voyageRepository.findById(voyageId)
                .orElseThrow(() -> new VoyageNotFoundException(voyageId));
        Camion camion = camionRepository.findById(camionId)
                .orElseThrow(() -> new RuntimeException("Camion not found"));

        voyage.setCamion(camion);
        Voyage updated = voyageRepository.save(voyage);
        return voyageMapper.toDto(updated);
    }

    @Override
    public VoyageDTO assignRemorque(Long voyageId, Long remorqueId) {
        Voyage voyage = voyageRepository.findById(voyageId)
                .orElseThrow(() -> new VoyageNotFoundException(voyageId));
        Remorque remorque = remorqueRepository.findById(remorqueId)
                .orElseThrow(() -> new RuntimeException("Remorque not found"));

        voyage.setRemorque(remorque);
        Voyage updated = voyageRepository.save(voyage);
        return voyageMapper.toDto(updated);
    }

    @Override
    public VoyageDTO addMarchandise(Long voyageId, Contient contient) {
        Voyage voyage = voyageRepository.findById(voyageId)
                .orElseThrow(() -> new VoyageNotFoundException(voyageId));

        voyage.getListMarchandises().add(contient);
        Voyage updated = voyageRepository.save(voyage);
        return voyageMapper.toDto(updated);
    }

    @Override
    public long countVoyagesByStatus(EtatVoyage status) {
        return voyageRepository.countByEtat(status);
    }

    @Override
    public Map<EtatVoyage, Long> getVoyagesStatistics() {
        // More efficient implementation using JPA aggregation
        return Arrays.stream(EtatVoyage.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        etat -> voyageRepository.countByEtat(etat)
                ));
    }

    @Override
    public VoyageEtatDTO changeVoyageEtat(VoyageEtatDTO voyageEtatDTO) {
        Voyage voyage = voyageRepository.findById(voyageEtatDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("Voyage not found"));

        // Check if trying to change to EN_COURS with warnings
        if (voyageEtatDTO.etat() == EtatVoyage.EN_COURS && !voyage.getWarnings().isEmpty()) {
            throw new IllegalStateException("Cannot start voyage with active warnings");
        }

        // Validate state transition
        if (!isValidTransition(voyage.getEtat(), voyageEtatDTO.etat())) {
            throw new IllegalStateException("Invalid state transition");
        }
        // Set arrival date if changing to TERMINE
        if (voyageEtatDTO.etat() == EtatVoyage.TERMINE) {
            voyage.setDateArriveReelle(LocalDate.now());
        }

        voyage.setEtat(voyageEtatDTO.etat());
        voyage = voyageRepository.save(voyage);
        return voyageMapper.toVoyageEtatDTO(voyage);
    }

    @Override
    public VoyageDTO checkVoyageWarnings(Long voyageId) {
        Voyage voyage = voyageRepository.findById(voyageId)
                .orElseThrow(() -> new EntityNotFoundException("Voyage not found"));

        voyage.checkForWarnings();
        return voyageMapper.toDto(voyage);
    }

    @Override
    public List<Voyage> getVoaygeInLast30Days() {
        LocalDate today= LocalDate.now();
        LocalDate in30Days = today.plusDays(30);
        return voyageRepository.findAllByDateArrivePrevueBetween(in30Days,today);
    }

}
