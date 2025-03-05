package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.camion.*;

import java.util.List;
import java.util.Optional;


public interface camionService {

    ///camion service
    public Camion addNewCamion(Camion camion);
    public List<Camion> getAllCamions();
    public Optional<Camion> getCamionById(Long immatriculation);
    public Camion updateCamion(Long immatriculation, Camion camionDetails);
    public void deleteCamion(Long immatriculation);

    /// TRUCK SERVICE
    public Truck addNewTruck(Truck truck);
    public List<Truck> getAllTrucks();
    public Optional<Truck> getTruckById(Long immatriculation);
    public Truck updateTruck(Long immatriculation, Truck truckDetails);
    public void deleteTruck(Long immatriculation);

    ///  REMORQUE SERVICE
    public Remorque addNewRemorque(Remorque remorque);
    public List<Remorque> getAllRemorques();
    public Optional<Remorque> getRemorqueById(Long immatriculation);
    public Remorque updateRemorque(Long immatriculation, Remorque remorqueDetails);
    public void deleteRemorque(Long immatriculation);



    /// Asurrance CRUD
    public Assurance addNewAssurance(Assurance assurance);
    public List<Assurance> getAllAssurances();
    public Optional<Assurance> getAssuranceById(int numeroContrat);
    public Assurance updateAssurance(int numeroContrat, Assurance assuranceDetails);
    public void deleteAssurance(int numeroContrat);


    /// carburan sevice CRUD
    public Carburant createCarburan(Carburant carburan);
    public List<Carburant> getAllCarburans();
    public Optional<Carburant> getCarburanById(Long id);
    public Carburant updateCarburan(Long id, Carburant carburanDetails);
    public void deleteCarburan(Long id);

    /// CARTE GRISE SERVICE CRUD

    public CarteGrise addNewCarteGrise(CarteGrise carteGrise);
    public List<CarteGrise> getAllCarteGrises();
    public Optional<CarteGrise> getCarteGriseById(Long id);
    public CarteGrise updateCarteGrise(Long id, CarteGrise carteGriseDetails);
    public void deleteCarteGrise(Long id);

    ///  ENTRETIEN CRUD /
    public Entretien createEntretien(Entretien entretien);
    public List<Entretien> getAllEntretiens();
    public Optional<Entretien> getEntretienById(Long id);
    public Entretien updateEntretien(Long id, Entretien entretienDetails);
    public void deleteEntretien(Long id);

    ///  KILOMETRAGE SERVICE CRUD

    public Kilometrage addNewKilometrage(Kilometrage kilometrage);
    public List<Kilometrage> getAllKilometrages();
    public Optional<Kilometrage> getKilometrageById(Long id);
    public Kilometrage updateKilometrage(Long id, Kilometrage kilometrageDetails);
    public void deleteKilometrage(Long id);


}
