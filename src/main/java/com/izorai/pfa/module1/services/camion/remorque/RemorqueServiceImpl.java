package com.izorai.pfa.module1.services.camion.remorque;

import com.izorai.pfa.module1.DTO.camion.remorque.RemorqueCreateDto;
import com.izorai.pfa.module1.DTO.camion.remorque.RemorqueDTO;
import com.izorai.pfa.module1.entities.camion.Remorque;
import com.izorai.pfa.module1.mappers.camion.RemorqueMapper;
import com.izorai.pfa.module1.mappers.camion.TypeRemorqueMapper;
import com.izorai.pfa.module1.repository.camion.RemorqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class RemorqueServiceImpl implements RemorqueService {
    private final RemorqueMapper remorqueMapper;
    private final RemorqueRepository remorqueRepository;
    private final TypeRemorqueMapper typeRemorqueMapper;


    @Override
    public RemorqueDTO addNewRemorque(RemorqueDTO remorqueDTO) {
        Remorque remorque = remorqueMapper.toEntity(remorqueDTO); // Convertit le DTO en entité
        remorqueRepository.save(remorque);
        return remorqueMapper.fromEntity(remorque); // Retourne le DTO après la sauvegarde
    }

    @Override
    public List<RemorqueDTO> getAllRemorques() {

        return remorqueRepository.findAll().stream().map(remorqueMapper::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<RemorqueDTO> getRemorqueById(Long id) {
        return remorqueRepository.findById(id).map(remorqueMapper::fromEntity);
    }

    @Override
    public RemorqueDTO updateRemorque(Long idRemorque, RemorqueDTO remorqueDTO) {
        Remorque updatedRemorque = remorqueRepository.findById(idRemorque).map(remorque -> {
            remorque.setTypeRemorque(typeRemorqueMapper.toEntity(remorqueDTO.typeRemorque()));
            remorque.setPoidsVide(remorqueDTO.poidsVide());
            remorque.setPoidsChargeMax(remorqueDTO.poidsChargeMax());
            remorque.setVolumeStockage(remorqueDTO.volumeStockage());
            return remorqueRepository.save(remorque);
        }).orElseThrow(() -> new RuntimeException("Remorque non trouvée"));

        return remorqueMapper.fromEntity(updatedRemorque);
    }

    @Override
    public void deleteRemorque(Long idRemorque) {
        remorqueRepository.deleteById(idRemorque);
    }



    @Override
    public List<Remorque> getRemorquesDisponibles() {
        return remorqueRepository.getRemorquesByDisponible(true);
    }



}
