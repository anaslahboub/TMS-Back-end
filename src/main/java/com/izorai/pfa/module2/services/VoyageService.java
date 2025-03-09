package com.izorai.pfa.module2.services;

import com.izorai.pfa.module2.entities.Voyage;

import java.util.List;

public interface VoyageService {
    public Voyage createVoyage(Voyage voyage);
    public List<Voyage> getAllVoyages();
    public Voyage getVoyageById(int id);
    public Voyage updateVoyage(Voyage voyageDetails);
    public void deleteVoyage(int id);
}
