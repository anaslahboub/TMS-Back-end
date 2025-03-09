package com.izorai.pfa.module1.services.partenaire.partenaire;

import com.izorai.pfa.module1.DTO.paretenaire.paretenaire.PartenaireCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.paretenaire.PartenaireRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Partenaire;
import com.izorai.pfa.module1.mappers.partenaire.PartenaireMapper;
import com.izorai.pfa.module1.repository.partenaire.PartenaireRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PartenaireServiceImpl implements PartenaireService {
    private final PartenaireRepository partenaireRepository;
    private final PartenaireMapper partenaireMapper;

    public PartenaireServiceImpl(PartenaireRepository partenaireRepository, PartenaireMapper partenaireMapper) {
        this.partenaireRepository = partenaireRepository;
        this.partenaireMapper = partenaireMapper;
    }


    @Override
    public PartenaireCreateDTO createPartenaire(PartenaireCreateDTO partenaireCreateDTO) {
        Partenaire partenaire = partenaireRepository.save(partenaireMapper.fromPartenaireCreateDTO(partenaireCreateDTO));
        return partenaireMapper.toPartenaireCreateDTO(partenaire);
    }



    @Override
    public List<PartenaireRespDTO> getAllPartenaires() {
        return partenaireRepository.findAll().
                stream().map(partenaireMapper::toPartenaireRespDTO).collect(Collectors.toList());
    }

    @Override
    public PartenaireRespDTO getPartenaireById(Long idPartenaire) {
        Partenaire partenaire = partenaireRepository.findById(idPartenaire)
                .orElseThrow(() -> new RuntimeException("Partenaire with id " + idPartenaire + " not found"));

        return partenaireMapper.toPartenaireRespDTO(partenaire);
    }

    @Override
    public Partenaire updatePartenaire(Partenaire partenaireDetails) {
        Partenaire partenaire = partenaireRepository.findById(partenaireDetails.getIdPartenaire()).get();
        partenaire.setNom(partenaireDetails.getNom());
        partenaire.setEmail(partenaireDetails.getEmail());
        partenaire.setTelephone(partenaireDetails.getTelephone());
        partenaire.setAdresses(partenaireDetails.getAdresses());
        partenaire.setTypePartenaires(partenaireDetails.getTypePartenaires());
        return partenaire;
    }

    @Override
    public void deletePartenaire(Long idPartenaire) {
        if (!partenaireRepository.existsById(idPartenaire)) {
            throw new EntityNotFoundException("Partenaire with id " + idPartenaire + " not found");
        }
        partenaireRepository.deleteById(idPartenaire);
    }
}
