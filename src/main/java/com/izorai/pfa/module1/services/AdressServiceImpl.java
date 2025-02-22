package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Adress;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdressServiceImpl implements AdressService {
    @Override
    public Adress addNewAdress(Adress adress) {
        return null;
    }

    @Override
    public List<Adress> getAllAdresses() {
        return List.of();
    }

    @Override
    public Optional<Adress> getAdressById(Long idAdress) {
        return Optional.empty();
    }

    @Override
    public Adress updateAdress(Long idAdress, Adress adressDetails) {
        return null;
    }

    @Override
    public void deleteAdress(Long idAdress) {

    }
}
