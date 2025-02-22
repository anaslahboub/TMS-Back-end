package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.marchandises.Unite;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UniteServiceImpl implements UniteService {
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
