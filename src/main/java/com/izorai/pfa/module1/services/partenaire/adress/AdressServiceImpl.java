package com.izorai.pfa.module1.services.partenaire.adress;

import com.izorai.pfa.module1.DTO.partenaire.adress.AdressCreateDto;
import com.izorai.pfa.module1.DTO.partenaire.adress.AdressUpdateDto;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.entities.partenaire.Morale;
import com.izorai.pfa.module1.entities.partenaire.Physique;
import com.izorai.pfa.module1.mappers.partenaire.AdressMapper;
import com.izorai.pfa.module1.repository.partenaire.AdressRepository;
import com.izorai.pfa.module1.repository.partenaire.MoraleRepository;
import com.izorai.pfa.module1.repository.partenaire.PhysiqueRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdressServiceImpl implements AdressService {
    private final AdressRepository adressRepository;
    private final AdressMapper adressMapper;
    private final PhysiqueRepository physiqueRepository;
    private final MoraleRepository moraleRepository;




    @Override
    public Adress addNewAdress(AdressCreateDto adress) {
        return adressRepository.save(adressMapper.fromAdressCreateDto(adress));
    }

    @Override
    public List<Adress> getAllAdresses() {

        return adressRepository.findAll();
    }

    @Override
    public Adress getAdressById(Long idAdress) {
        return
                adressRepository.findById(idAdress).orElseThrow(
                        ()->{throw new RuntimeException("Adress with id "+idAdress+" not found");});
    }

    @Override
    @Transactional
    public Adress updateAdress(Long idAdress, AdressUpdateDto adressDetails) {
        Adress adress = getAdressById(idAdress);
        adress.setPays(adressDetails.getPays());
        adress.setVille(adressDetails.getVille());
        adress.setCodePostal(adressDetails.getCodePostal());
        adress.setRue(adressDetails.getRue());
        adressRepository.save(adress);
        return adress;
    }

    @Override
    @Transactional
    public void deleteAdress(Long idAdress) {
        // Rechercher dans les partenaires Physique
        Physique partenairePhysique = physiqueRepository.findByAdressesIdAdress(idAdress);
        if (partenairePhysique != null) {
            partenairePhysique.getAdresses().removeIf(adresse -> adresse.getIdAdress().equals(idAdress));
            physiqueRepository.save(partenairePhysique); // Sauvegarder les modifications
            return;
        }

        // Rechercher dans les partenaires Morale
        Morale partenaireMorale = moraleRepository.findByAdressesIdAdress(idAdress);
        if (partenaireMorale != null) {
            partenaireMorale.getAdresses().removeIf(adresse -> adresse.getIdAdress().equals(idAdress));
            moraleRepository.save(partenaireMorale); // Sauvegarder les modifications
            return;
        }

        // Si l'adresse n'est trouvée dans aucun partenaire
        throw new RuntimeException("Adresse non trouvée dans aucun Partenaire");
    }
}
