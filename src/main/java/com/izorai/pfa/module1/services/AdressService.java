package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Adress;

import java.util.List;
import java.util.Optional;

public interface AdressService {
    Adress addNewAdress(Adress adress);
    List<Adress> getAllAdresses();
    Optional<Adress> getAdressById(Long idAdress);
    Adress updateAdress(Long idAdress, Adress adressDetails);
    void deleteAdress(Long idAdress);
}
