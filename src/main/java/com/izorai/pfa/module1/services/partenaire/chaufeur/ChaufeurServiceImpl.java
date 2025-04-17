package com.izorai.pfa.module1.services.partenaire.chaufeur;

import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurCreateDTO;
import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurPermisDto;
import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurRespDTO;
import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurUpdateDto;
import com.izorai.pfa.module1.entities.camion.Chaufeur;
import com.izorai.pfa.module1.exceptions.partenaire.ChaufeurNotFoundException;
import com.izorai.pfa.module1.mappers.partenaire.ChaufeurMapper;
import com.izorai.pfa.module1.repository.partenaire.ChaufeurRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChaufeurServiceImpl implements ChaufeurService {
    private static final Logger logger = LoggerFactory.getLogger(ChaufeurServiceImpl.class);
    private final ChaufeurMapper chaufeurMapper;
    private final ChaufeurRepository chaufeurRepository;




    @Override
    @Transactional
    public ChaufeurRespDTO addNewChaufeur(ChaufeurCreateDTO chauffeurCreateDTO) {
        Chaufeur chauffeur = chaufeurMapper.fromChaufeurCreateDTO(chauffeurCreateDTO);
        chauffeur = chaufeurRepository.save(chauffeur);
        return chaufeurMapper.toChaufeurRespDTO(chauffeur);
    }

    @Override
    public List<ChaufeurRespDTO> getAllChaufeurs() {
        List<Chaufeur> chauffeurs = chaufeurRepository.findAll();
        return chauffeurs.stream()
                .map(chaufeurMapper::toChaufeurRespDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChaufeurRespDTO getChaufeurById(Long idPartenaire) {
        Chaufeur chauffeur = chaufeurRepository.findByIdPartenaire(idPartenaire);
        return chaufeurMapper.toChaufeurRespDTO(chauffeur);
    }

    @Override
    @Transactional
    public ChaufeurRespDTO updateChaufeur(Long idPartenaire, ChaufeurUpdateDto chauffeurCreateDTO) {
        Chaufeur chauffeur = chaufeurRepository.findById(idPartenaire)
                .orElseThrow(() -> new RuntimeException("Chauffeur not found"));
        chauffeur.setNom(chauffeurCreateDTO.getNom());
        chauffeur.setEmail(chauffeurCreateDTO.getEmail());
        chauffeur.setTelephone(chauffeurCreateDTO.getTelephone());
        chauffeur.setPrenom(chauffeurCreateDTO.getPrenom());
        chauffeur.setCni(chauffeurCreateDTO.getCNI());
        chauffeur.setCnss(chauffeurCreateDTO.getCnss());
        chauffeur.setDisponibilite(chauffeurCreateDTO.getDisponibilite());
        chauffeur.setDateRecrutement(chauffeurCreateDTO.getDateRecrutement());
        chauffeur.setDateExpirationPermis(chauffeurCreateDTO.getDateExpirationPermis());
        chauffeur = chaufeurRepository.save(chauffeur);
        return chaufeurMapper.toChaufeurRespDTO(chauffeur);
    }

    @Override
    @Transactional
    public void deleteChaufeur(Long idPartenaire) {
        chaufeurRepository.findById(idPartenaire)
                .ifPresent(chauffeur -> chaufeurRepository.delete(chauffeur));
    }


    // Availability Operations
    @Override
    @Transactional
    public List<ChaufeurRespDTO> getChaufeursDisponibles() {
        logger.info("Fetching all available drivers");
        return chaufeurRepository.findByDisponibilite("true")
                .stream()
                .map(chaufeurMapper::toChaufeurRespDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void changeDisponibilite(Long idPartenaire) {
        logger.info("Toggling availability for driver {}", idPartenaire);

        Chaufeur chaufeur = chaufeurRepository.findById(idPartenaire)
                .orElseThrow(() -> new ChaufeurNotFoundException(idPartenaire));

        // Toggle the disponibilite value
        String newAvailability = "true".equalsIgnoreCase(chaufeur.getDisponibilite())
                ? "false"
                : "true";

        chaufeur.setDisponibilite(newAvailability);
        chaufeurRepository.save(chaufeur);

        logger.debug("Driver {} availability changed from {} to {}",
                idPartenaire,
                chaufeur.getDisponibilite(),
                newAvailability);
    }



    // Insurance Management
    @Override
    public boolean isPermisValid(Long idPartenaire) {
        logger.info("Vérification de la validité du permis pour le chauffeur {}", idPartenaire);

        Chaufeur chaufeur = chaufeurRepository.findById(idPartenaire)
                .orElseThrow(() -> new ChaufeurNotFoundException(idPartenaire));

        LocalDate expiration = chaufeur.getDateExpirationPermis();
        return expiration != null && !expiration.isBefore(LocalDate.now());
    }


    @Override
    public List<ChaufeurRespDTO> findDriversWithExpiredPermis() {
        logger.info("Fetching drivers with expired permis");

        return chaufeurRepository.findAll().stream()
                .filter(chaufeur ->
                        chaufeur.getDateExpirationPermis() == null ||
                                chaufeur.getDateExpirationPermis().isBefore(LocalDate.now())
                )
                .map(chaufeurMapper::toChaufeurRespDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public Chaufeur updatePermisExpirationDate(Long id, LocalDate newExpirationDate) {
        logger.info("Updating insurance expiration date for driver {}", id);

        // Validate input date
        if (newExpirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null");
        }

        if (newExpirationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiration date cannot be in the past");
        }

        return chaufeurRepository.findById(id)
                .map(chaufeur -> {
                    chaufeur.setDateExpirationPermis(newExpirationDate);
                    return chaufeurRepository.save(chaufeur);
                })
                .orElseThrow(() -> new ChaufeurNotFoundException(id));
    }



    // Statistical Operations
    @Override
    public int getNombreChaufeursActifs() {
        logger.info("Counting active drivers");
        // Assuming "true" in disponibilite means active/available
        return chaufeurRepository.countByDisponibilite("true");
    }

    @Override
    public int getNombreChaufeursEnMission() {
        logger.info("Counting drivers on mission");
        // Assuming "false" in disponibilite means on mission/unavailable
        return chaufeurRepository.countByDisponibilite("false");
    }
    @Override
    public  ChaufeurPermisDto getPermisPhoto(Long idChaufeur){
        Chaufeur chaufeur=chaufeurRepository.findByIdPartenaire(idChaufeur);
        ChaufeurPermisDto chaufeurPermisDto=new ChaufeurPermisDto();
        chaufeurPermisDto.setIdPartenaire(chaufeur.getIdPartenaire());
        chaufeurPermisDto.setPhotoPermisVerso(chaufeur.getPhotoPermisVerso());
        chaufeurPermisDto.setPhotoPermisRecto(chaufeur.getPhotoPermisRecto());

        return chaufeurPermisDto;
    }

    @Override

    public List<Chaufeur> getChauffeurPermisExpiranteBefore30Days(){
        LocalDate today = LocalDate.now();
        LocalDate day =  today.minusDays(30);
        return chaufeurRepository.findAllByDateExpirationPermisBetween(today,day);
    }

    @Override
    public List<Chaufeur> getChauffeurPermisExpiranteAfter30Days(){
        LocalDate today = LocalDate.now();
        LocalDate day =  today.plusDays(30);
        return chaufeurRepository.findAllByDateExpirationPermisBetween(day,today);

    }




}
