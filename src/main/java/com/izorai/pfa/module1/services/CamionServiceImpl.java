package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.*;
import com.izorai.pfa.module1.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CamionServiceImpl implements camionService {
    private CamionRepository camionRepository;
    private TruckRepository truckRepository;
    private RemorqueRepository remorqueRepository;
    private AssuranceRepository assuranceRepository;
    private CarburantRepository carburantRepository;
    private CarteGriseRepository carteGriseRepository;
    private EntretienRepository entretienRepository;
    private KilometrageRepository kilometrageRepository;

    public CamionServiceImpl(
            CamionRepository camionRepository,
            TruckRepository truckRepository,
            RemorqueRepository remorqueRepository,
            AssuranceRepository assuranceRepository,
            CarburantRepository carburantRepository,
            CarteGriseRepository carteGriseRepository,
            EntretienRepository entretienRepository,
            KilometrageRepository kilometrageRepository) {
        this.camionRepository = camionRepository;
        this.truckRepository = truckRepository;
        this.remorqueRepository = remorqueRepository;
        this.assuranceRepository = assuranceRepository;
        this.carburantRepository = carburantRepository;
        this.carteGriseRepository = carteGriseRepository;
        this.entretienRepository = entretienRepository;
        this.kilometrageRepository = kilometrageRepository;
    }

    @Override
    public Camion addNewCamion(Camion camion) {
        camionRepository.save(camion);
        return null;
    }

    @Override
    public List<Camion> getAllCamions() {
        return camionRepository.findAll();
    }

    @Override
    public Optional<Camion> getCamionById(String immatriculation) {
        return camionRepository.findByImmatriculation(immatriculation);
    }

    @Override
    public Camion updateCamion(String immatriculation, Camion camionDetails) {
        camionRepository.findByImmatriculation(immatriculation).map(
                camion -> {
                    camion.setAssurance(camionDetails.getAssurance());
                    camion.setCarteGrise(camionDetails.getCarteGrise());
                    return camionRepository.save(camion);
                }
        ).orElseThrow(() -> new RuntimeException("camion non trouve"));
        return null;
    }

    @Override
    public void deleteCamion(String immatriculation) {
        camionRepository.deleteByImmatriculation(immatriculation);
    }

    @Override
    public Truck addNewTruck(Truck truck) {
        truckRepository.save(truck);
        return null;
    }

    @Override
    public List<Truck> getAllTrucks() {

        return truckRepository.findAll();
    }

    @Override
    public Optional<Truck> getTruckById(String immatriculation) {

        return truckRepository.findByImmatriculation(immatriculation);
    }

    @Override
    public Truck updateTruck(String immatriculation, Truck truckDetails) {
        truckRepository.findByImmatriculation(immatriculation).map(
                truck->{
                    truck.setAssurance(truckDetails.getAssurance());
                    truck.setCarteGrise(truckDetails.getCarteGrise());
                    truck.setConsommation(truckDetails.getConsommation());
                    truck.setPoidsMax(truckDetails.getPoidsMax());
                    truck.setTypeCabine(truckDetails.getTypeCabine());
                    return truckRepository.save(truck);
                }
        ).orElseThrow(() -> new RuntimeException("truck non trouve"));
        return null;
    }

    @Override
    public void deleteTruck(String immatriculation) {
        truckRepository.deleteByImmatriculation(immatriculation);
    }

    @Override
    public Remorque addNewRemorque(Remorque remorque) {

        return remorqueRepository.save(remorque);
    }

    @Override
    public List<Remorque> getAllRemorques() {
        return remorqueRepository.findAll();
    }

    @Override
    public Optional<Remorque> getRemorqueById(String immatriculation) {
        return remorqueRepository.findByImmatriculation(immatriculation);
    }

    @Override
    public Remorque updateRemorque(String immatriculation, Remorque remorqueDetails) {
        remorqueRepository.findByImmatriculation(immatriculation).map(
                remorque->{
                    remorque.setAssurance(remorqueDetails.getAssurance());
                    remorque.setCarteGrise(remorqueDetails.getCarteGrise());
                    remorque.setTypeRemorque(remorque.getTypeRemorque());
                    remorque.setPoidsVide(remorqueDetails.getPoidsVide());
                    remorque.setPoidsChargeMax(remorqueDetails.getPoidsChargeMax());
                    remorque.setVolumesStockage(remorqueDetails.getVolumesStockage());
                    return remorqueRepository.save(remorque);
                }
        ).orElseThrow(()->new RuntimeException("remorque non trouve"));
        return null;
    }

    @Override
    public void deleteRemorque(String immatriculation) {
        remorqueRepository.deleteByImmatriculation(immatriculation);
    }

    @Override
    public Assurance addNewAssurance(Assurance assurance) {
        assuranceRepository.save(assurance);
        return null;
    }

    @Override
    public List<Assurance> getAllAssurances() {

        return assuranceRepository.findAll();
    }

    @Override
    public Optional<Assurance> getAssuranceById(int numeroContrat) {
        assuranceRepository.getAssuranceByNumeroContrat(numeroContrat);
        return Optional.empty();
    }

    @Override
    public Assurance updateAssurance(int numeroContrat, Assurance assuranceDetails) {
        getAssuranceById(numeroContrat).map(
                assurance -> {
                    assurance.setCompany(assuranceDetails.getCompany());
                    assurance.setMontant(assuranceDetails.getMontant());
                    assurance.setDateDebut(assuranceDetails.getDateDebut());
                    assurance.setDateExpiration(assuranceDetails.getDateExpiration());
                    assurance.setPrimeAnnuelle(assuranceDetails.getPrimeAnnuelle());
                    assurance.setTypeCouverture(assuranceDetails.getTypeCouverture());
                    assurance.setNumCarteVerte(assuranceDetails.getNumCarteVerte());
                    assurance.setStatutCarteVerte(assuranceDetails.getStatutCarteVerte());
                    return assuranceRepository.save(assurance);
                }
        ).orElseThrow(()-> new RuntimeException("assurance non trouvee"));
        return null;
    }

    @Override
    public void deleteAssurance(int numeroContrat) {
        assuranceRepository.deleteByNumeroContrat(numeroContrat);
    }

    @Override
    public Carburant createCarburant(Carburant Carburant) {
        carburantRepository.save(Carburant);
        return null;
    }

    @Override
    public List<Carburant> getAllCarburants() {
        return carburantRepository.findAll();
    }

    @Override
    public Optional<Carburant> getCarburantById(Long id) {
        return carburantRepository.findById(id);
    }

    @Override
    public Carburant updateCarburant(Long id, Carburant carburantDetails) {
        carburantRepository.findById(id).map(
                carburant -> {
                    carburant.setCamion(carburantDetails.getCamion());
                    carburant.setTypeCarburant(carburantDetails.getTypeCarburant());
                    carburant.setQuantity(carburantDetails.getQuantity());
                    carburant.setDateRemplissage(carburantDetails.getDateRemplissage());
                    carburant.setKilometrageActuel(carburantDetails.getKilometrageActuel());
                    carburant.setPrixParLitre(carburantDetails.getPrixParLitre());
                    return carburantRepository.save(carburant);
                }
        ).orElseThrow(()-> new RuntimeException("carburant non trouve"));
        return null;
    }

    @Override
    public void deleteCarburant(Long id) {
        carburantRepository.deleteById(id);
    }

    @Override
    public CarteGrise addNewCarteGrise(CarteGrise carteGrise) {
        return carteGriseRepository.save(carteGrise);
    }

    @Override
    public List<CarteGrise> getAllCarteGrises() {
        return carteGriseRepository.findAll();
    }

    @Override
    public Optional<CarteGrise> getCarteGriseById(Long id) {
        return carteGriseRepository.findById(id);
    }

    @Override
    public CarteGrise updateCarteGrise(Long id, CarteGrise carteGriseDetails) {
        return carteGriseRepository.findById(id).map(carteGrise -> {
            carteGrise.setDateMiseEnCirculation(carteGriseDetails.getDateMiseEnCirculation());
            carteGrise.setMarque(carteGriseDetails.getMarque());
            carteGrise.setGenre(carteGriseDetails.getGenre());
            carteGrise.setNumeroSerie(carteGriseDetails.getNumeroSerie());
            carteGrise.setCouleur(carteGriseDetails.getCouleur());
            carteGrise.setNombrePlace(carteGriseDetails.getNombrePlace());
            carteGrise.setPuissanceFiscale(carteGriseDetails.getPuissanceFiscale());
            carteGrise.setEnergie(carteGriseDetails.getEnergie());
            carteGrise.setProprietaire(carteGriseDetails.getProprietaire());
            carteGrise.setPoidsVide(carteGriseDetails.getPoidsVide());
            carteGrise.setPoidsAutorise(carteGriseDetails.getPoidsAutorise());
            carteGrise.setDateDelivrance(carteGriseDetails.getDateDelivrance());
            carteGrise.setAdress(carteGriseDetails.getAdress());
            return carteGriseRepository.save(carteGrise);
        }).orElseThrow(() -> new RuntimeException("Carte grise non trouvée"));
    }

    @Override
    public void deleteCarteGrise(Long id) {
        carteGriseRepository.deleteById(id); // Supprime la carte grise par son ID
    }

    @Override
    public Entretien createEntretien(Entretien entretien) {
        return entretienRepository.save(entretien); // Sauvegarde l'entretien et le retourne
    }

    @Override
    public List<Entretien> getAllEntretiens() {
        return entretienRepository.findAll(); // Retourne tous les entretiens
    }

    @Override
    public Optional<Entretien> getEntretienById(Long id) {
        return entretienRepository.findById(id); // Retourne l'entretien par son ID
    }

    @Override
    public Entretien updateEntretien(Long id, Entretien entretienDetails) {
        return entretienRepository.findById(id).map(entretien -> {
            entretien.setDateEntretien(entretienDetails.getDateEntretien());
            entretien.setTypeEntretien(entretienDetails.getTypeEntretien());
            entretien.setDescription(entretienDetails.getDescription());
            entretien.setCout(entretienDetails.getCout());
            entretien.setDateProchainEntretien(entretienDetails.getDateProchainEntretien());
            entretien.setCamion(entretienDetails.getCamion());
            return entretienRepository.save(entretien);
        }).orElseThrow(() -> new RuntimeException("Entretien non trouvé"));
    }

    @Override
    public void deleteEntretien(Long id) {
        entretienRepository.deleteById(id);
    }

    @Override
    public Kilometrage addNewKilometrage(Kilometrage kilometrage) {
        return kilometrageRepository.save(kilometrage);
    }
    @Override
    public List<Kilometrage> getAllKilometrages() {
        return kilometrageRepository.findAll();
    }

    @Override
    public Optional<Kilometrage> getKilometrageById(Long id) {
        return kilometrageRepository.findById(id);
    }

    @Override
    public Kilometrage updateKilometrage(Long id, Kilometrage kilometrageDetails) {
        return kilometrageRepository.findById(id).map(kilometrage -> {
            kilometrage.setDateEnregistrement(kilometrageDetails.getDateEnregistrement());
            kilometrage.setKilometrageActuel(kilometrageDetails.getKilometrageActuel());
            kilometrage.setDernierEntretien(kilometrageDetails.getDernierEntretien());
            kilometrage.setProchainEntretien(kilometrageDetails.getProchainEntretien());
            kilometrage.setNotes(kilometrageDetails.getNotes());
            kilometrage.setCamion(kilometrageDetails.getCamion());
            return kilometrageRepository.save(kilometrage);
        }).orElseThrow(() -> new RuntimeException("Kilométrage non trouvé"));
    }

    @Override
    public void deleteKilometrage(Long id) {
        kilometrageRepository.deleteById(id); 
    }
}
