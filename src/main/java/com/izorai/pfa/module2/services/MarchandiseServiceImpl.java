package com.izorai.pfa.module2.services;

import com.izorai.pfa.module2.entities.marchandises.Categorie;
import com.izorai.pfa.module2.entities.marchandises.Marchandise;
import com.izorai.pfa.module2.entities.marchandises.Unite;

import java.util.List;
import java.util.Optional;

public class MarchandiseServiceImpl implements MarchandiseService {
    @Override
    public Marchandise addNewMarchandise(Marchandise marchandise) {
        return null;
    }

    @Override
    public List<Marchandise> getAllMarchandises() {
        return List.of();
    }

    @Override
    public Optional<Marchandise> getMarchandiseById(int id) {
        return Optional.empty();
    }

    @Override
    public Marchandise updateMarchandise(int id, Marchandise marchandiseDetails) {
        return null;
    }

    @Override
    public void deleteMarchandise(int id) {

    }

    @Override
    public Categorie addNewCategorie(Categorie categorie) {
        return null;
    }

    @Override
    public List<Categorie> getAllCategories() {
        return List.of();
    }

    @Override
    public Optional<Categorie> getCategorieById(int id) {
        return Optional.empty();
    }

    @Override
    public Categorie updateCategorie(int id, Categorie categorieDetails) {
        return null;
    }

    @Override
    public void deleteCategorie(int id) {

    }

    @Override
    public Unite addNewUnite(Unite unite) {
        return null;
    }

    @Override
    public List<Unite> getAllUnites() {
        return List.of();
    }

    @Override
    public Optional<Unite> getUniteById(int id) {
        return Optional.empty();
    }

    @Override
    public Unite updateUnite(int id, Unite uniteDetails) {
        return null;
    }

    @Override
    public void deleteUnite(int id) {

    }
}
