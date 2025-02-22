package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Remorque;

import java.util.List;
import java.util.Optional;

public interface RemorqueService {
    public Remorque addNewRemorque(Remorque remorque);
    public List<Remorque> getAllRemorques();
    public Optional<Remorque> getRemorqueById(Long immatriculation);
    public Remorque updateRemorque(Long immatriculation, Remorque remorqueDetails);
    public void deleteRemorque(Long immatriculation);
}
