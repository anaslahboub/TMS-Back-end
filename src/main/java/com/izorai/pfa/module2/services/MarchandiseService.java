package com.izorai.pfa.module2.services;

import com.izorai.pfa.module2.entities.marchandises.Categorie;
import com.izorai.pfa.module2.entities.marchandises.Marchandise;
import com.izorai.pfa.module2.entities.marchandises.Unite;

import java.util.List;
import java.util.Optional;

public interface MarchandiseService {
    public Marchandise addNewMarchandise(Marchandise marchandise);
    public List<Marchandise> getAllMarchandises();
    public Optional<Marchandise> getMarchandiseById(int id);
    public Marchandise updateMarchandise(int id, Marchandise marchandiseDetails);
    public void deleteMarchandise(int id);

    /// CATEGORIE CRUD
    public Categorie addNewCategorie(Categorie categorie);
    public List<Categorie> getAllCategories();
    public Optional<Categorie> getCategorieById(int id);
    public Categorie updateCategorie(int id, Categorie categorieDetails);
    public void deleteCategorie(int id);


    ///  UNITE SERVICE CRUD

    public Unite addNewUnite(Unite unite);
    public List<Unite> getAllUnites();
    public Optional<Unite> getUniteById(int id);
    public Unite updateUnite(int id, Unite uniteDetails);
    public void deleteUnite(int id);
}
