package com.izorai.pfa.module1.services.partenaire.adress;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.repository.partenaire.AdressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressServiceImpl implements AdressService {
    private final AdressRepository adressRepository;

    public AdressServiceImpl(AdressRepository adressRepository) {
        this.adressRepository = adressRepository;
    }



    @Override
    public Adress addNewAdress(Adress adress) {
        return adressRepository.save(adress);
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
    public Adress updateAdress(Adress adressDetails) {
        Adress adress = getAdressById(adressDetails.getIdAdress());
        adress.setPays(adressDetails.getPays());
        adress.setVille(adressDetails.getVille());
        adress.setCodePostal(adressDetails.getCodePostal());
        adress.setRue(adressDetails.getRue());
        adress.setType(adressDetails.getType());
        return adress;
    }

    @Override
    public void deleteAdress(Long idAdress) {
        adressRepository.findById(idAdress).ifPresent(adress -> adressRepository.deleteById(idAdress));
    }
}
