package com.izorai.pfa;

import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueRespDTO;
import com.izorai.pfa.module1.entities.camion.*;
import com.izorai.pfa.module1.entities.enumerations.StatusEntretien;
import com.izorai.pfa.module1.entities.enumerations.TypeCarburant;
import com.izorai.pfa.module1.entities.partenaire.*;
import com.izorai.pfa.module1.repository.camion.*;
import com.izorai.pfa.module1.repository.partenaire.*;
import com.izorai.pfa.module1.services.partenaire.physique.PhysiqueService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final CamionRepository camionRepository;
    private final CarburantRepository carburantRepository;
    private final AssuranceRepository assuranceRepository;
    private final CarteGriseRepository carteGriseRepository;
    private final EntretienRepository entretienRepository;
    private final RemorqueRepository remorqueRepository;
    private AdressRepository adressRepository;
    private TypePartenaireRepository typePartenaireRepository;
    private PartenaireRepository partenaireRepository;
    private final  PhysiqueRepository physiqueRepository;
    private MoraleRepository moraleRepository;
    private final PhysiqueService physiqueService;



    public void run(String... args) throws Exception {

        Adress adress1 = new Adress();
        adress1.setType("Principale");
        adress1.setRue("123 Rue de Paris");
        adress1.setVille("Paris");
        adress1.setPays("France");
        adress1.setCodePostal("75001");
        adressRepository.save(adress1);

        Adress adress2 = new Adress();
        adress2.setType("Secondaire");
        adress2.setRue("456 Avenue des Champs-Élysées");
        adress2.setVille("Paris");
        adress2.setPays("France");
        adress2.setCodePostal("75008");
        adressRepository.save(adress2);

        // Créer des types de partenaires
        TypePartenaire type1 = new TypePartenaire();
        type1.setLibelle("Partenaire A");
        type1.setDefinition("Description du partenaire A");
        type1.setGenre("Type A");
        typePartenaireRepository.save(type1);

        TypePartenaire type2 = new TypePartenaire();
        type2.setLibelle("Partenaire B");
        type2.setDefinition("Description du partenaire B");
        type2.setGenre("Type B");
        typePartenaireRepository.save(type2);

        TypePartenaire type3 = new TypePartenaire();
        type3.setLibelle("Partenaire C");
        type3.setDefinition("Description du partenaire C");
        type3.setGenre("Type C");
        typePartenaireRepository.save(type3);

        Physique physique1 = new Physique();
        physique1.setNom("Dupont");
        physique1.setEmail("dupont@example.com");
        physique1.setTelephone("0123456789");
        physique1.setCNI("123456789");
        physique1.setPrenom("Jean");
        physique1.setAdresses(new ArrayList<Adress>());
        physiqueRepository.save(physique1);

        PhysiqueCreateDTO physique2 = new PhysiqueCreateDTO("Martin","martin@example.com","0682582462","123456","ahmed");
        PhysiqueRespDTO physiqueRespDTO= physiqueService.addNewPhysique(physique2);

        // Créer des partenaires moraux
        Morale morale1 = new Morale();
        morale1.setNom("ABC Corp");
        morale1.setEmail("abc@example.com");
        morale1.setTelephone("0112233445");
        morale1.setICE(123456);
        morale1.setNumeroRC(987654321L);
        morale1.setAbreviation("ABC");
        morale1.setFormeJuridique("SARL");
        morale1.setAdresses(new ArrayList<Adress>());
        partenaireRepository.save(morale1);

        Morale morale2 = new Morale();
        morale2.setNom("XYZ Corp");
        morale2.setEmail("xyz@example.com");
        morale2.setTelephone("0555666777");
        morale2.setICE(654321);
        morale2.setNumeroRC(123456789L);
        morale2.setAbreviation("XYZ");
        morale2.setFormeJuridique("SA");
        morale2.setAdresses(new ArrayList<Adress>());
        partenaireRepository.save(morale2);

        physique1.getAdresses().add(adress1);
        morale1.getAdresses().add(adress1);
        morale2.getAdresses().add(adress2);

        physique1.setTypePartenaire(type1);
        morale1.setTypePartenaire(type2);
        morale2.setTypePartenaire(type3);

        partenaireRepository.save(physique1);
        partenaireRepository.save(morale1);
        partenaireRepository.save(morale2);





       for ( int i =1;i<=6;i++){
        CarteGrise carteGrise = new CarteGrise();
        carteGrise.setDateMiseEnCirculation(LocalDate.of(2015 + i, 5, 10));
        carteGrise.setMarque("Marque " + i);
        carteGrise.setGenre("Poids Lourd");
        carteGrise.setNumeroSerie(100000000L + i);
        carteGrise.setCouleur("Couleur " + i);
        carteGrise.setNombrePlace(2);
        carteGrise.setPuissanceFiscale("12CV");
        carteGrise.setEnergie("Diesel");
        carteGrise.setProprietaire("Entreprise " + i);
        carteGrise.setPoidsVide(3000 + i * 10);
        carteGrise.setPoidsAutorise(7000 + i * 10);
        carteGrise.setDateDelivrance(LocalDate.of(2016 + i, 6, 1));

        carteGriseRepository.save(carteGrise);


            // Création d'une assurance
            Assurance assurance = new Assurance();
            assurance.setNumeroContrat(987654320L + i);
            assurance.setCompany("Assurance " + i);
            assurance.setTypeCouverture("Tous risques");
            assurance.setMontant(5000 + i * 100);
            assurance.setDateDebut(LocalDate.of(2023, 1, 1));
            assurance.setDateExpiration(LocalDate.of(2024, 1, 1));
            assurance.setPrimeAnnuelle(500 + i * 50);
            assurance.setNumCarteVerte(123456780L + i);
            assurance.setActive(i % 2 == 0);

            assuranceRepository.save(assurance);

           // Création de 6 remorques
            Remorque remorque = new Remorque();
            remorque.setTypeRemorque("Type Remorque " + i);
            remorque.setVolumesStockage(100 + i * 10);
            remorque.setPoidsVide(2000 + i * 50);
            remorque.setPoidsChargeMax(6000 + i * 50);
            remorque.setDisponible(i % 2 == 0);

            remorqueRepository.save(remorque);

           // Création d'un camion
            Camion camion = new Camion();
            camion.setImmatriculation("ABC-" + i + "-234");
            camion.setTypeCabine("Type " + i);
            camion.setPoidsMax(5000 + i * 100);
            camion.setConsommation(15 + i);
            camion.setDisponible(i % 2 == 0);
            camion.setCarteGrise(carteGrise);
            camion.setAssurance(assurance);

            camionRepository.save(camion);

           for (int j = 1; j <= 6; j++) {
                Carburant carburant = new Carburant();
                carburant.setDateRemplissage(LocalDate.now().minusDays(j * 10));
                carburant.setQuantiteLitres(50 + j * 10);
                carburant.setPrixParLitre(15 + j);
                carburant.setKilometrageActuel(50000 + j * 100);
                carburant.setTypeCarburant(j % 2 == 0 ? TypeCarburant.DIESEL : TypeCarburant.ESSENCE);
                carburant.setCamion(camion);

                carburantRepository.save(carburant);
            }

           for (int j = 1; j <= 6; j++) {
                Entretien entretien = new Entretien();
                entretien.setDateEntretien(LocalDate.now().minusMonths(j));
                entretien.setTypeEntretien(j % 2 == 0 ? "Vidange" : "Réparation Mécanique");
                entretien.setDescription("Entretien numéro " + j + " pour le camion " + i);
                entretien.setCout(500 + j * 50);
                entretien.setDateProchainEntretien(LocalDate.now().plusMonths(j * 2));
                entretien.setStatusEntretien(j % 2 == 0 ? StatusEntretien.EN_ATTENTE : StatusEntretien.EFFECTUE);
                entretien.setCamion(camion);

                entretienRepository.save(entretien);
            }


       }





        System.out.println("✅ 6 camions, 6 cartes grises, 6 assurances et 36 enregistrements de carburant insérés !");
    }
}
