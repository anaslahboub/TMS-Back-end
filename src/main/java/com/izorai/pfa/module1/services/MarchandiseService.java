package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.marchandises.Marchandise;

import java.util.List;
import java.util.Optional;

public interface MarchandiseService {
    public Marchandise addNewMarchandise(Marchandise marchandise);
    public List<Marchandise> getAllMarchandises();
    public Optional<Marchandise> getMarchandiseById(int id);
    public Marchandise updateMarchandise(int id, Marchandise marchandiseDetails);
    public void deleteMarchandise(int id);
}
