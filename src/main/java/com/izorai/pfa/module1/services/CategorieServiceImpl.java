package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.marchandises.Categorie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategorieServiceImpl implements CategorieService {
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
}
