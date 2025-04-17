package com.izorai.pfa.module2.services.demande;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.entities.partenaire.Physique;
import com.izorai.pfa.module1.repository.partenaire.AdressRepository;
import com.izorai.pfa.module1.repository.partenaire.PhysiqueRepository;
import com.izorai.pfa.module2.DTO.demande.DemandeCotationDto;
import com.izorai.pfa.module2.entities.DemandeCotation;
import com.izorai.pfa.module2.enumerations.StatusDemandeCotation;
import com.izorai.pfa.module2.exceptions.DemandeNotFoundException;
import com.izorai.pfa.module2.exceptions.PhysiqueNotFoundException;
import com.izorai.pfa.module2.mappers.DemandeCotationMapper;
import com.izorai.pfa.module2.repository.DemandeCotationRepository;
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

public class DemandeCotationServiceImpl implements DemandeCotationService {
    private final DemandeCotationRepository demandeRepository;
    private final DemandeCotationMapper demandeMapper;
    private final PhysiqueRepository physiqueRepository;
    private final AdressRepository adressRepository;


    @Override
    public DemandeCotationDto createDemande(DemandeCotationDto demandeDto) {

        DemandeCotation demande = demandeMapper.fromDemandeCotationDto(demandeDto);
        demande.setDateDemande(LocalDate.now());
        demande.setStatut(StatusDemandeCotation.EN_ATTENTE);

        // Save the DemandeCotation
        DemandeCotation savedDemande = demandeRepository.save(demande);

        // Return the saved demande as DTO
        return demandeMapper.toDemandeCotationDto(savedDemande);
    }


    @Override
    public DemandeCotationDto updateDemande(Long id, DemandeCotationDto demandeDto) {
        return demandeRepository.findById(id)
                .map(existingDemande -> {
                    demandeMapper.updateFromDto(demandeDto, existingDemande);
                    DemandeCotation updated = demandeRepository.save(existingDemande);
                    return demandeMapper.toDemandeCotationDto(updated);
                })
                .orElseThrow(() -> new DemandeNotFoundException(id));
    }

    @Override
    public void deleteDemande(Long id) {
        if (!demandeRepository.existsById(id)) {
            throw new DemandeNotFoundException(id);
        }
        demandeRepository.deleteById(id);
    }

    @Override
    public Optional<DemandeCotationDto> getDemandeById(Long id) {
        return demandeRepository.findById(id)
                .map(demandeMapper::toDemandeCotationDto);
    }

    @Override
    public List<DemandeCotationDto> getAllDemandes() {
        return demandeRepository.findAll(Sort.by(Sort.Direction.DESC, "dateDemande"))
                .stream()
                .map(demandeMapper::toDemandeCotationDto)
                .collect(Collectors.toList());
    }

    @Override
    public DemandeCotationDto updateStatus(Long id, StatusDemandeCotation newStatus) {
        return demandeRepository.findById(id)
                .map(demande -> {
                    demande.setStatut(newStatus);
                    DemandeCotation updated = demandeRepository.save(demande);
                    return demandeMapper.toDemandeCotationDto(updated);
                })
                .orElseThrow(() -> new DemandeNotFoundException(id));
    }

    @Override
    public List<DemandeCotationDto> getDemandesByStatus(StatusDemandeCotation status) {
        return demandeRepository.findByStatut(status)
                .stream()
                .map(demandeMapper::toDemandeCotationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DemandeCotationDto> searchByTypeMarchandise(String type) {
        return demandeRepository.findByTypeMarchandiseContainingIgnoreCase(type).stream().map(demandeMapper::toDemandeCotationDto).collect(Collectors.toList());
    }

    @Override
    public long countDemandesByStatus(StatusDemandeCotation status) {
        return demandeRepository.countByStatut(status);
    }

    @Override
    public Map<StatusDemandeCotation, Long> getDemandesStatistics() {
        return demandeRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        DemandeCotation::getStatut,
                        Collectors.counting()
                ));
    }

    @Override
    public DemandeCotationDto assignPhysique(Long demandeId, Long physiqueId) {
        DemandeCotation demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new DemandeNotFoundException(demandeId));

        Physique physique = physiqueRepository.findById(physiqueId)
                .orElseThrow(() -> new PhysiqueNotFoundException(physiqueId));

        // Affectation du physique à la demande
        demande.setPhysique(physique);

        // Sauvegarde de la mise à jour
        DemandeCotation updatedDemande = demandeRepository.save(demande);

        // Conversion en DTO
        return demandeMapper.toDemandeCotationDto(updatedDemande);
    }


    @Override
    public DemandeCotationDto updateChargementAddress(Long demandeId, Adress newAddress) {
        DemandeCotation demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new DemandeNotFoundException(demandeId));

        demande.setAdresseChargement(newAddress);

        DemandeCotation updatedDemande = demandeRepository.save(demande);

        return demandeMapper.toDemandeCotationDto(updatedDemande);
    }

    @Override
    public DemandeCotationDto updateDechargementAddress(Long demandeId, Adress newAddress) {

        DemandeCotation demandeCotation = demandeRepository.findById(demandeId).orElseThrow(() -> new DemandeNotFoundException(demandeId));


        demandeCotation.setAdresseChargement(newAddress);
        return demandeMapper.toDemandeCotationDto(demandeCotation);
    }
}
