package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.marchandises.Categorie;

import java.util.List;
import java.util.Optional;

public interface CategorieService {
    Categorie addNewCategorie(Categorie categorie);
    List<Categorie> getAllCategories();
    Optional<Categorie> getCategorieById(int id);
    Categorie updateCategorie(int id, Categorie categorieDetails);
    void deleteCategorie(int id);
}
