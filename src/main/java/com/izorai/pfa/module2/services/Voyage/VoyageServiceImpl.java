package com.izorai.pfa.module2.services.Voyage;

import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Chaufeur;
import com.izorai.pfa.module1.entities.camion.Remorque;
import com.izorai.pfa.module1.repository.camion.CamionRepository;
import com.izorai.pfa.module1.repository.camion.RemorqueRepository;
import com.izorai.pfa.module1.repository.partenaire.ChaufeurRepository;
import com.izorai.pfa.module1.services.partenaire.chaufeur.ChaufeurService;
import com.izorai.pfa.module2.DTO.voyage.VoyageDTO;
import com.izorai.pfa.module2.entities.Voyage;
import com.izorai.pfa.module2.entities.contient.Contient;
import com.izorai.pfa.module2.enumerations.EtatVoyage;
import com.izorai.pfa.module2.exceptions.VoyageNotFoundException;
import com.izorai.pfa.module2.mappers.VoyageMapper;
import com.izorai.pfa.module2.repository.VoyageRepository;
import com.izorai.pfa.module2.services.VoyageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    @Override
    public VoyageDTO createVoyage(VoyageDTO voyageDTO) {
        Voyage voyage = voyageMapper.toEntity(voyageDTO);
        voyage.setEtat(EtatVoyage.PLANIFIE);
        Voyage savedVoyage = voyageRepository.save(voyage);
        return voyageMapper.toDto(savedVoyage);
    }

    @Override
    public VoyageDTO updateVoyage(Long id, VoyageDTO voyageDTO) {
        return voyageRepository.findById(id)
                .map(existingVoyage -> {
                    voyageMapper.updateFromDto(voyageDTO, existingVoyage);
                    Voyage updated = voyageRepository.save(existingVoyage);
                    return voyageMapper.toDto(updated);
                })
                .orElseThrow(() -> new VoyageNotFoundException(id));
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
                    voyage.setEtat(newStatus);
                    Voyage updated = voyageRepository.save(voyage);
                    return voyageMapper.toDto(updated);
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
        return voyageRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Voyage::getEtat,
                        Collectors.counting()
                ));
    }
}