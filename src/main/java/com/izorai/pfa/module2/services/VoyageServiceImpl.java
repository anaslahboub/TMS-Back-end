package com.izorai.pfa.module2.services;

import com.izorai.pfa.module2.entities.Voyage;
import com.izorai.pfa.module2.repository.VoyageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoyageServiceImpl implements VoyageService {

    private final VoyageRepository voyageRepository;
    public VoyageServiceImpl(VoyageRepository voyageRepository) {
        this.voyageRepository = voyageRepository;
    }
    @Override
    public Voyage createVoyage(Voyage voyage) {
        return voyageRepository.save(voyage);
    }

    @Override
    public List<Voyage> getAllVoyages() {
        return voyageRepository.findAll();
    }

    @Override
    public Voyage getVoyageById(int id) {

        return voyageRepository.findById((id)).orElseThrow(() ->
                new IllegalArgumentException("Voyage with id " + id + " not found")
        );

    }

    @Override
    public Voyage updateVoyage(Voyage voyageDetails) {
        Voyage voyage = getVoyageById(voyageDetails.getId());
        voyage.setChaufeur(voyageDetails.getChaufeur());
        voyage.setEtat(voyageDetails.getEtat());
        voyage.setDistance(voyageDetails.getDistance());
        voyage.setDateArrivePrevue(voyageDetails.getDateArrivePrevue());
        voyage.setDateDepart(voyageDetails.getDateDepart());
        voyage.setDateArriveRelle(voyageDetails.getDateArriveRelle());
        voyage.setLieuArrive(voyageDetails.getLieuArrive());
        voyage.setLieuDepart(voyageDetails.getLieuDepart());
        voyage.setRemorque(voyageDetails.getRemorque());

        return voyage;
    }

    @Override
    public void deleteVoyage(int id) {
        voyageRepository.deleteById(id);
    }
}
