package com.izorai.pfa;

import com.izorai.pfa.module1.entities.camion.*;
import com.izorai.pfa.module1.entities.enumerations.StatusEntretien;
import com.izorai.pfa.module1.entities.enumerations.TypeCarburant;
import com.izorai.pfa.module1.repository.camion.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final CamionRepository camionRepository;
    private final CarburantRepository carburantRepository;
    private final AssuranceRepository assuranceRepository;
    private final CarteGriseRepository carteGriseRepository;
    private final EntretienRepository entretienRepository;
    private final RemorqueRepository remorqueRepository;



    @Override

    public void run(String... args) throws Exception {
//
//       for ( int i =1;i<=6;i++){
//        CarteGrise carteGrise = new CarteGrise();
//        carteGrise.setDateMiseEnCirculation(LocalDate.of(2015 + i, 5, 10));
//        carteGrise.setMarque("Marque " + i);
//        carteGrise.setGenre("Poids Lourd");
//        carteGrise.setNumeroSerie(100000000L + i);
//        carteGrise.setCouleur("Couleur " + i);
//        carteGrise.setNombrePlace(2);
//        carteGrise.setPuissanceFiscale("12CV");
//        carteGrise.setEnergie("Diesel");
//        carteGrise.setProprietaire("Entreprise " + i);
//        carteGrise.setPoidsVide(3000 + i * 10);
//        carteGrise.setPoidsAutorise(7000 + i * 10);
//        carteGrise.setDateDelivrance(LocalDate.of(2016 + i, 6, 1));
//
//        carteGriseRepository.save(carteGrise);
//
//
//            // Création d'une assurance
//            Assurance assurance = new Assurance();
//            assurance.setNumeroContrat(987654320L + i);
//            assurance.setCompany("Assurance " + i);
//            assurance.setTypeCouverture("Tous risques");
//            assurance.setMontant(5000 + i * 100);
//            assurance.setDateDebut(LocalDate.of(2023, 1, 1));
//            assurance.setDateExpiration(LocalDate.of(2024, 1, 1));
//            assurance.setPrimeAnnuelle(500 + i * 50);
//            assurance.setNumCarteVerte(123456780L + i);
//            assurance.setActive(i % 2 == 0);
//
//            assuranceRepository.save(assurance);
//
//           // Création de 6 remorques
//            Remorque remorque = new Remorque();
//            remorque.setTypeRemorque("Type Remorque " + i);
//            remorque.setVolumesStockage(100 + i * 10);
//            remorque.setPoidsVide(2000 + i * 50);
//            remorque.setPoidsChargeMax(6000 + i * 50);
//            remorque.setDisponible(i % 2 == 0);
//
//            remorqueRepository.save(remorque);
//
//           // Création d'un camion
//            Camion camion = new Camion();
//            camion.setImmatriculation("ABC-" + i + "-234");
//            camion.setTypeCabine("Type " + i);
//            camion.setPoidsMax(5000 + i * 100);
//            camion.setConsommation(15 + i);
//            camion.setDisponible(i % 2 == 0);
//            camion.setCarteGrise(carteGrise);
//            camion.setAssurance(assurance);
//
//            camionRepository.save(camion);
//
//           for (int j = 1; j <= 6; j++) {
//                Carburant carburant = new Carburant();
//                carburant.setDateRemplissage(LocalDate.now().minusDays(j * 10));
//                carburant.setQuantiteLitres(50 + j * 10);
//                carburant.setPrixParLitre(15 + j);
//                carburant.setKilometrageActuel(50000 + j * 100);
//                carburant.setTypeCarburant(j % 2 == 0 ? TypeCarburant.DIESEL : TypeCarburant.ESSENCE);
//                carburant.setCamion(camion);
//
//                carburantRepository.save(carburant);
//            }
//
//           for (int j = 1; j <= 6; j++) {
//                Entretien entretien = new Entretien();
//                entretien.setDateEntretien(LocalDate.now().minusMonths(j));
//                entretien.setTypeEntretien(j % 2 == 0 ? "Vidange" : "Réparation Mécanique");
//                entretien.setDescription("Entretien numéro " + j + " pour le camion " + i);
//                entretien.setCout(500 + j * 50);
//                entretien.setDateProchainEntretien(LocalDate.now().plusMonths(j * 2));
//                entretien.setStatusEntretien(j % 2 == 0 ? StatusEntretien.EN_ATTENTE : StatusEntretien.EFFECTUE);
//                entretien.setCamion(camion);
//
//                entretienRepository.save(entretien);
//            }
//
//
//       }





        System.out.println("✅ 6 camions, 6 cartes grises, 6 assurances et 36 enregistrements de carburant insérés !");
    }
}
