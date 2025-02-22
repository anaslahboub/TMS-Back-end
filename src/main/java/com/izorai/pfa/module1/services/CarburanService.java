package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Carburan;

import java.util.List;
import java.util.Optional;

public interface CarburanService {
    public Carburan createCarburan(Carburan carburan);
    public List<Carburan> getAllCarburans();
    public Optional<Carburan> getCarburanById(Long id);
    public Carburan updateCarburan(Long id, Carburan carburanDetails);
    public void deleteCarburan(Long id);
}
