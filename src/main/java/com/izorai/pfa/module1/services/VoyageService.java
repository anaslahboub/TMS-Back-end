package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Voyage;

import java.util.List;
import java.util.Optional;

public interface VoyageService {
    public Voyage createVoyage(Voyage voyage);
    public List<Voyage> getAllVoyages();
    public Voyage getVoyageById(int id);
    public Voyage updateVoyage(Voyage voyageDetails);
    public void deleteVoyage(int id);
}
