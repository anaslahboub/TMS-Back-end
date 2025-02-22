package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Assurance;

import java.util.List;
import java.util.Optional;

public interface AssuranceService {
    Assurance addNewAssurance(Assurance assurance);
    List<Assurance> getAllAssurances();
    Optional<Assurance> getAssuranceById(int numeroContrat);
    Assurance updateAssurance(int numeroContrat, Assurance assuranceDetails);
    void deleteAssurance(int numeroContrat);
}
