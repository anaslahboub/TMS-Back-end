package com.izorai.pfa.module1.services.camion.remorque;

import com.izorai.pfa.module1.DTO.camion.remorque.RemorqueDTO;
import com.izorai.pfa.module1.entities.camion.Remorque;
import com.izorai.pfa.module1.mappers.camion.RemorqueMapper;
import com.izorai.pfa.module1.repository.camion.RemorqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class RemorqueServiceImpl implements RemorqueService {
    private final RemorqueMapper remorqueMapper;
    private final RemorqueRepository remorqueRepository;

    public RemorqueServiceImpl(RemorqueMapper remorqueMapper, RemorqueRepository remorqueRepository) {
        this.remorqueMapper = remorqueMapper;
        this.remorqueRepository = remorqueRepository;
    }


    @Override
    public RemorqueDTO addNewRemorque(RemorqueDTO remorqueDTO) {
        Remorque remorque = remorqueMapper.fromRemorqueDTO(remorqueDTO); // Convertit le DTO en entité
        remorqueRepository.save(remorque);
        return remorqueMapper.toRemorqueDTO(remorque); // Retourne le DTO après la sauvegarde
    }

    @Override
    public List<RemorqueDTO> getAllRemorques() {

        return remorqueRepository.findAll().stream().map(remorqueMapper::toRemorqueDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<RemorqueDTO> getRemorqueById(Long id) {
        return remorqueRepository.findById(id).map(remorqueMapper::toRemorqueDTO);
    }

    @Override
    public RemorqueDTO updateRemorque(Long immatriculation, RemorqueDTO remorqueDTO) {
        Remorque updatedRemorque = remorqueRepository.findById(immatriculation).map(remorque -> {
            remorque.setTypeRemorque(remorqueDTO.typeRemorque());
            remorque.setPoidsVide(remorqueDTO.poidsVide());
            remorque.setPoidsChargeMax(remorqueDTO.poidsChargeMax());
            remorque.setVolumesStockage(remorqueDTO.volumesStockage());
            return remorqueRepository.save(remorque);
        }).orElseThrow(() -> new RuntimeException("Remorque non trouvée"));

        return remorqueMapper.toRemorqueDTO(updatedRemorque);
    }

    @Override
    public void deleteRemorque(Long immatriculation) {
        remorqueRepository.deleteById(immatriculation);
    }



    @Override
    public List<Remorque> getRemorquesDisponibles() {
        return remorqueRepository.getRemorquesByDisponible(true);
    }



}
