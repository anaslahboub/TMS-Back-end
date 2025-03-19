package com.izorai.pfa.module1.services.camion.remorque;

import com.izorai.pfa.module1.DTO.camion.remorque.RemorqueCreateDto;
import com.izorai.pfa.module1.DTO.camion.remorque.RemorqueDTO;
import com.izorai.pfa.module1.entities.camion.Remorque;

import java.util.List;
import java.util.Optional;

public interface RemorqueService {

    ///  REMORQUE SERVICE
    public RemorqueDTO addNewRemorque(RemorqueDTO remorque);
    public List<RemorqueDTO> getAllRemorques();
    public Optional<RemorqueDTO> getRemorqueById(Long id);
    public RemorqueDTO updateRemorque(Long id, RemorqueDTO remorqueDetails);
    public void deleteRemorque(Long id );

    List<Remorque> getRemorquesDisponibles();
}
