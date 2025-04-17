package com.izorai.pfa.module1.services.camion.assurance;

import com.izorai.pfa.module1.DTO.camion.assurance.AssuranceDTO;
import com.izorai.pfa.module1.entities.camion.Assurance;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.mappers.camion.AssuranceMapper;
import com.izorai.pfa.module1.repository.camion.AssuranceRepository;
import com.izorai.pfa.module1.repository.camion.CamionRepository;
import com.izorai.pfa.module1.services.notifications.NotificationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class AssuranceServiceImpl implements AssuranceService {

    private final AssuranceMapper assuranceMapper;
    private final AssuranceRepository assuranceRepository;
    private final CamionRepository camionRepository;
    private final NotificationService notificationService;

    @Override
    public AssuranceDTO addNewAssurance(AssuranceDTO assurance) {
        Assurance ass = assuranceMapper.fromAssuranceDto(assurance);
        return assuranceMapper.toAssuranceDto(assuranceRepository.save(ass));
    }

    @Override
    public List<AssuranceDTO> getAllAssurances() {

        return assuranceRepository.findAll().stream().
                map(assuranceMapper::toAssuranceDto).collect(Collectors.toList());
    }

    @Override
    public AssuranceDTO getAssuranceById(Long numeroContrat) {
        Assurance assurance = assuranceRepository.findById(numeroContrat).orElseThrow(()->
                new RuntimeException("Assurance n existe pas "));

        return assuranceMapper.toAssuranceDto(assurance);
    }


    @Override
    @Transactional
    public AssuranceDTO updateAssurance(Long numeroContrat, AssuranceDTO assuranceDetails) {
        Assurance assurance = assuranceRepository.findById(numeroContrat).get();

        // Mise à jour des valeurs de l'entité avec les valeurs du DTO
        assurance.setCompany(assuranceDetails.getCompany());
        assurance.setMontant(assuranceDetails.getMontant());
        assurance.setDateDebut(assuranceDetails.getDateDebut());
        assurance.setDateExpiration(assuranceDetails.getDateExpiration());
        assurance.setPrimeAnnuelle(assuranceDetails.getPrimeAnnuelle());
        assurance.setTypeCouverture(assuranceDetails.getTypeCouverture());
        assurance.setNumCarteVerte(assuranceDetails.getNumCarteVerte());
        assurance.setPhotoAssurance(assuranceDetails.getPhotoAssurance());

        // Mapper l'entité mise à jour en DTO pour le renvoyer
        return assuranceMapper.toAssuranceDto((assurance));  // Assurez-vous d'avoir un mapper pour cela
    }


    @Override
    @Transactional
    public void deleteAssurance(Long numeroContrat) {
        Camion camion = camionRepository.findByAssuranceNumeroContrat(numeroContrat);
        if (camion != null) {
            camion.setAssurance(null);
            camionRepository.save(camion);
        }
        assuranceRepository.deleteByNumeroContrat(numeroContrat);
    }

    @Override
    public AssuranceDTO getAssuranceByCamion(String immatriculationCamion) {
        Camion camion =camionRepository.findByImmatriculation(immatriculationCamion).get();
        Assurance assurance = camion.getAssurance();
        return assuranceMapper.toAssuranceDto(assurance);
    }

    @Override
    @Transactional
    public AssuranceDTO renouvelerAssurance(Long numeroContrat, LocalDate nouvelleDate) {
        Assurance assurance = assuranceRepository.findById(numeroContrat).get();
        assurance.setDateExpiration(nouvelleDate);
        assurance.setDateDebut(LocalDate.now());
        return assuranceMapper.toAssuranceDto(assurance);
    }

    @Override
    public double getTotalPrimesAnnuelles() {
        List<Assurance> assurancesActives = assuranceRepository.findByActive(true);

        return assurancesActives.stream()
                .mapToDouble(Assurance::getPrimeAnnuelle)
                .sum();
    }




    @Override
    public List<Assurance> getAssurancesExpirantDans30Jours() {
        LocalDate today = LocalDate.now();
        LocalDate alertDate = today.plusDays(30); // Calcul de la date dans 30 jours

        // Récupérer les assurances qui expirent entre aujourd'hui et dans 30 jours
        List<Assurance> assurancesExpirantDans30Jours = assuranceRepository
                .findByDateExpirationBetweenAndActive(today, alertDate, true);

        // Mapper les entités Assurance vers AssuranceDTO
        return assurancesExpirantDans30Jours;
    }
    @Override
    public List<Assurance> getAssurancesExpirantBefore30Jours(){
        LocalDate today = LocalDate.now();
        LocalDate alertDate = today.minusDays(30);
        return assuranceRepository.findByDateExpirationBetweenAndActive(today, alertDate, true);
    }
}
