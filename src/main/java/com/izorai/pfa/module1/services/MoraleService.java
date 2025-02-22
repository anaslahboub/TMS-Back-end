package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Morale;

import java.util.List;
import java.util.Optional;

public interface MoraleService {
    Morale addNewMorale(Morale morale);
    List<Morale> getAllMorales();
    Optional<Morale> getMoraleById(int ICE);
    Morale updateMorale(int ICE, Morale moraleDetails);
    void deleteMorale(int ICE);
}
