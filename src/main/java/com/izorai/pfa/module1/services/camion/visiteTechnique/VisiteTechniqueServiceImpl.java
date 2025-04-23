package com.izorai.pfa.module1.services.camion.visiteTechnique;

import com.izorai.pfa.module1.DTO.camion.visiteTechnique.VisiteTechniqueDto;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.VisiteTechnique;
import com.izorai.pfa.module1.mappers.camion.VisiteTechniqueMapper;
import com.izorai.pfa.module1.repository.camion.VisiteTechniqueRepository;
import com.izorai.pfa.module1.repository.camion.CamionRepository;
import com.izorai.pfa.module1.services.notifications.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class VisiteTechniqueServiceImpl implements VisiteTechniqueService {


    private final VisiteTechniqueRepository visiteTechniqueRepository;
    private final CamionRepository camionRepository;
    private final NotificationService notificationService;
    private final VisiteTechniqueMapper visiteTechniqueMapper;

    @Override
    public VisiteTechniqueDto save(VisiteTechnique visiteTechnique) {
        // Validate input
        if (visiteTechnique.getCamion() == null || visiteTechnique.getCamion().getImmatriculation() == null) {
            throw new IllegalArgumentException("Camion is required for visite technique");
        }
        if (visiteTechnique.getDateVisite() == null || visiteTechnique.getDateExpiration() == null) {
            throw new IllegalArgumentException("Date de visite and date d'expiration are required");
        }
        if (visiteTechnique.getDateExpiration().isBefore(visiteTechnique.getDateVisite())) {
            throw new IllegalArgumentException("Expiration date must be after visite date");
        }
        if (visiteTechnique.getResultatVisite() == null) {
            throw new IllegalArgumentException("Resultat visite is required");
        }

        // Verify camion exists
        String immatriculation = visiteTechnique.getCamion().getImmatriculation();
        Optional<Camion> camionOpt = camionRepository.findByImmatriculation(immatriculation);
        if (camionOpt.isEmpty()) {
            throw new IllegalArgumentException("Camion with immatriculation " + immatriculation + " not found");
        }
        visiteTechnique.setCamion(camionOpt.get());

        return visiteTechniqueMapper.toDto(visiteTechniqueRepository.save(visiteTechnique));
    }

    @Override
    public VisiteTechniqueDto getById(Long id) {
        return  visiteTechniqueMapper.toDto(visiteTechniqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visite technique with ID " + id + " not found")));
    }

    @Override
    public List<VisiteTechniqueDto> getAll() {
        return visiteTechniqueRepository.findAll().stream().map(visiteTechniqueMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<VisiteTechniqueDto> getByCamion(String immatriculation) {
        Optional<Camion> camionOpt = camionRepository.findByImmatriculation(immatriculation);
        if (camionOpt.isEmpty()) {
            throw new IllegalArgumentException("Camion with immatriculation " + immatriculation + " not found");
        }
        return camionOpt.get().getVisitesTechniques().stream().map(visiteTechniqueMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (!visiteTechniqueRepository.existsById(id)) {
            throw new IllegalArgumentException("Visite technique with ID " + id + " not found");
        }
        visiteTechniqueRepository.deleteById(id);
    }

    @Override
    public VisiteTechniqueDto update(Long id, VisiteTechnique visiteTechnique) {
        VisiteTechnique existing = visiteTechniqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visite technique with ID " + id + " not found"));


        existing.setObservations(visiteTechnique.getObservations());
        existing.setResultatVisite(visiteTechnique.getResultatVisite());
        existing.setDateExpiration(visiteTechnique.getDateExpiration());
        existing.setCentreVisite(visiteTechnique.getCentreVisite());
        existing.setDateVisite(visiteTechnique.getDateVisite());
        existing.setDocumentUrl(visiteTechnique.getDocumentUrl());
        if (visiteTechnique.getCamion().equals( existing.getCamion())) {
            existing.setCamion(visiteTechnique.getCamion());

        }

        return  visiteTechniqueMapper.toDto(existing);
    }


    @Override
    public List<VisiteTechnique> getVisitesTechniquesExpirantDans30Jours() {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusDays(30);

        List<VisiteTechnique> visiteTechniques = visiteTechniqueRepository.findByDateExpirationBetween(start, end);

        return visiteTechniques;
    }

    @Override
    public List<VisiteTechnique> getVisitesTechniquesExpirantBefore30Jours(){
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().minusDays(30);
        return visiteTechniqueRepository.findByDateExpirationBetween(start, end);
    }



}