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
        assurance.setCompany(assuranceDetails.company());
        assurance.setMontant(assuranceDetails.montant());
        assurance.setDateDebut(assuranceDetails.dateDebut());
        assurance.setDateExpiration(assuranceDetails.dateExpiration());
        assurance.setPrimeAnnuelle(assuranceDetails.primeAnnuelle());
        assurance.setTypeCouverture(assuranceDetails.typeCouverture());
        assurance.setNumCarteVerte(assuranceDetails.numCarteVerte());

        // Mapper l'entité mise à jour en DTO pour le renvoyer
        return assuranceMapper.toAssuranceDto((assurance));  // Assurez-vous d'avoir un mapper pour cela
    }


    @Override
    @Transactional
    public void deleteAssurance(Long numeroContrat) {
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
    @Scheduled(fixedRate = 86400000)
    public void checkExpirationAssurances() {
        LocalDate today = LocalDate.now();
        LocalDate alertDate = today.plusDays(30); // Alerte 30 jours avant expiration

        // Récupérer les assurances qui expirent dans moins de 30 jours
        List<Assurance> assurancesExpirant = assuranceRepository.findByDateExpirationBetweenAndActive(today, alertDate, true);

        for (Assurance assurance : assurancesExpirant) {
            long joursRestants = ChronoUnit.DAYS.between(today, assurance.getDateExpiration());

            // Envoyer une notification différente selon l'urgence
            if (joursRestants <= 7) {
                notificationService.envoyerAlerteStandard(assurance,joursRestants);
            } else if (joursRestants <= 15) {
                notificationService.envoyerAlerteStandard(assurance,joursRestants);
            } else {
                notificationService.envoyerAlerteStandard(assurance,joursRestants);
            }
        }

        // Vérifier les assurances expirées
        List<Assurance> assurancesExpirees = assuranceRepository.findByDateExpirationBeforeAndActive(today, true);

        for (Assurance assurance : assurancesExpirees) {
            // Marquer comme inactive
            assurance.setActive(false);
            assuranceRepository.save(assurance);

            // Notifier de l'expirationg
            notificationService.notifierExpirationAssurance(assurance);
        }
    }

    @Override
    public List<AssuranceDTO> getAssurancesExpirantDans30Jours() {
        LocalDate today = LocalDate.now();
        LocalDate alertDate = today.plusDays(30); // Calcul de la date dans 30 jours

        // Récupérer les assurances qui expirent entre aujourd'hui et dans 30 jours
        List<Assurance> assurancesExpirantDans30Jours = assuranceRepository
                .findByDateExpirationBetweenAndActive(today, alertDate, true);

        // Mapper les entités Assurance vers AssuranceDTO
        return assurancesExpirantDans30Jours.stream()
                .map(assuranceMapper::toAssuranceDto)
                .collect(Collectors.toList());
    }
}
