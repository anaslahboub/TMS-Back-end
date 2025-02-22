package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Assurance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AssuranceServiceImpl implements AssuranceService {
    @Override
    public Assurance addNewAssurance(Assurance assurance) {
        return null;
    }

    @Override
    public List<Assurance> getAllAssurances() {
        return List.of();
    }

    @Override
    public Optional<Assurance> getAssuranceById(int numeroContrat) {
        return Optional.empty();
    }

    @Override
    public Assurance updateAssurance(int numeroContrat, Assurance assuranceDetails) {
        return null;
    }

    @Override
    public void deleteAssurance(int numeroContrat) {

    }
}
