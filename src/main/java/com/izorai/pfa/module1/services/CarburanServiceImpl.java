package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Carburan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CarburanServiceImpl implements CarburanService {
    @Override
    public Carburan createCarburan(Carburan carburan) {
        return null;
    }

    @Override
    public List<Carburan> getAllCarburans() {
        return List.of();
    }

    @Override
    public Optional<Carburan> getCarburanById(Long id) {
        return Optional.empty();
    }

    @Override
    public Carburan updateCarburan(Long id, Carburan carburanDetails) {
        return null;
    }

    @Override
    public void deleteCarburan(Long id) {

    }
}
