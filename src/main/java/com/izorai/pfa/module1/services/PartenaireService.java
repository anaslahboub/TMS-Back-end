package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.*;

import java.util.List;
import java.util.Optional;

public interface PartenaireService {
    Partenaire createPartenaire(Partenaire partenaire);
    List<Partenaire> getAllPartenaires();
    Optional<Partenaire> getPartenaireById(Long idPartenaire);
    Partenaire updatePartenaire(Long idPartenaire, Partenaire partenaireDetails);
    void deletePartenaire(Long idPartenaire);


    /// MORALE SERVICE CRUD
    public Morale addNewMorale(Morale morale);
    public List<Morale> getAllMorales();
    public Optional<Morale> getMoraleById(int ICE);
    public Morale updateMorale(int ICE, Morale moraleDetails);
    public void deleteMorale(int ICE);

    /// PHYSIQUE SERVICE CRUD
    public Physique addNewPhysique(Physique physique);
    public List<Physique> getAllPhysiques();
    public  Optional<Physique> getPhysiqueById(int CIN);
    public  Physique updatePhysique(int CIN, Physique moraleDetails);
    public void deletePhysique(int CIN);

    /// CHAUFEUR SERVICE

    Chaufeur addNewChaufeur(Chaufeur chaufeur);
    List<Chaufeur> getAllChaufeurs();
    Optional<Chaufeur> getChaufeurById(Long id);
    Chaufeur updateChaufeur(Long id, Chaufeur chaufeurDetails);
    void deleteChaufeur(Long id);

    /// TYPE PARETANAIRE SERVICE
    ///
    ///
    TypePartenaire addNewTypePartenaire(TypePartenaire typePartenaire);
    List<TypePartenaire> getAllTypePartenaires();
    Optional<TypePartenaire> getTypePartenaireById(Long idTypePartenaire);
    TypePartenaire updateTypePartenaire(Long idTypePartenaire, TypePartenaire typePartenaireDetails);
    void deleteTypePartenaire(Long idTypePartenaire);


    /// ADRESS  SERVICE CRUD
    public Adress addNewAdress(Adress adress);
    public List<Adress> getAllAdresses();
    public Optional<Adress> getAdressById(Long idAdress);
    public Adress updateAdress(Long idAdress, Adress adressDetails);
    public void deleteAdress(Long idAdress);


}
