package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Morale;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MoraleServiceImpl implements MoraleService {
    @Override
    public Morale addNewMorale(Morale morale) {
        return null;
    }

    @Override
    public List<Morale> getAllMorales() {
        return List.of();
    }

    @Override
    public Optional<Morale> getMoraleById(int ICE) {
        return Optional.empty();
    }

    @Override
    public Morale updateMorale(int ICE, Morale moraleDetails) {
        return null;
    }

    @Override
    public void deleteMorale(int ICE) {

    }
}
