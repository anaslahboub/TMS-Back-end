package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Remorque;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RemorqueServiceImpl implements RemorqueService {
    @Override
    public Remorque addNewRemorque(Remorque remorque) {
        return null;
    }

    @Override
    public List<Remorque> getAllRemorques() {
        return List.of();
    }

    @Override
    public Optional<Remorque> getRemorqueById(Long immatriculation) {
        return Optional.empty();
    }

    @Override
    public Remorque updateRemorque(Long immatriculation, Remorque remorqueDetails) {
        return null;
    }

    @Override
    public void deleteRemorque(Long immatriculation) {

    }
}
