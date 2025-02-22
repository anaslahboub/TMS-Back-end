package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Voyage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VoyageServiceImpl implements VoyageService {
    @Override
    public Voyage createVoyage(Voyage voyage) {
        return null;
    }

    @Override
    public List<Voyage> getAllVoyages() {
        return List.of();
    }

    @Override
    public Optional<Voyage> getVoyageById(int id) {
        return Optional.empty();
    }

    @Override
    public Voyage updateVoyage(int id, Voyage voyageDetails) {
        return null;
    }

    @Override
    public void deleteVoyage(int id) {

    }
}
