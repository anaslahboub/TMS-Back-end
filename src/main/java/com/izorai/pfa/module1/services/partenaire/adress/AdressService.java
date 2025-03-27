package com.izorai.pfa.module1.services.partenaire.adress;

import com.izorai.pfa.module1.DTO.partenaire.adress.AdressCreateDto;
import com.izorai.pfa.module1.DTO.partenaire.adress.AdressUpdateDto;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import java.util.List;

public interface AdressService {

    /// ADRESS  SERVICE CRUD
    public Adress addNewAdress(AdressCreateDto adress);
    public List<Adress> getAllAdresses();
    public Adress getAdressById(Long idAdress);
    public Adress updateAdress(Long idAdress, AdressUpdateDto adressDetails);
    public void deleteAdress(Long idAdress);

}
