package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.marchandises.Unite;

import java.util.List;
import java.util.Optional;

public interface UniteService {
    public Unite addNewUnite(Unite unite);
    public List<Unite> getAllUnites();
    public Optional<Unite> getUniteById(int id);
    public Unite updateUnite(int id, Unite uniteDetails);
    public void deleteUnite(int id);
}
