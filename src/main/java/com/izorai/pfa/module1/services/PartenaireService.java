package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.paretenaire.PartenaireRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.typePartenaire.TypePartenaireCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.typePartenaire.TypePartenaireRespDTO;
import com.izorai.pfa.module1.entities.partenaire.*;
import com.izorai.pfa.module1.DTO.paretenaire.paretenaire.PartenaireCreateDTO;

import java.util.List;

public interface PartenaireService {
    PartenaireCreateDTO createPartenaire(PartenaireCreateDTO partenaire);
    List<PartenaireRespDTO> getAllPartenaires();
    PartenaireRespDTO getPartenaireById(Long idPartenaire);
    Partenaire updatePartenaire(Partenaire partenaireDetails);
    void deletePartenaire(Long idPartenaire);


    /// MORALE SERVICE CRUD
    public MoraleRespDTO addNewMorale(MoraleCreateDTO morale);
    public List<MoraleRespDTO> getAllMorales();
    public MoraleRespDTO getMoraleById(Long idPartenaire); // Utilisation de idPartenaire
    public MoraleRespDTO updateMorale(Long id,MoraleCreateDTO moraleDetails);
    public void deleteMorale(Long idPartenaire);

    /// PHYSIQUE SERVICE CRUD
    public PhysiqueRespDTO addNewPhysique(PhysiqueCreateDTO physique);
    public List<PhysiqueRespDTO> getAllPhysiques();
    public PhysiqueRespDTO getPhysiqueById(Long idPartenaire); // Utilisation de idPartenaire
    public PhysiqueRespDTO updatePhysique(Long id,  PhysiqueCreateDTO physiqueDetails);
    public void deletePhysique(Long idPartenaire);

    /// CHAUFEUR SERVICE

    ChaufeurRespDTO addNewChaufeur(ChaufeurCreateDTO chaufeur);
    List<ChaufeurRespDTO> getAllChaufeurs();
    ChaufeurRespDTO getChaufeurById(Long id);
    ChaufeurRespDTO updateChaufeur( Long id,ChaufeurCreateDTO chaufeurDetails);
    void deleteChaufeur(Long id);

    /// TYPE PARETANAIRE SERVICE
    ///
    ///
    TypePartenaireRespDTO addNewTypePartenaire(TypePartenaireCreateDTO typePartenaire);
    List<TypePartenaireRespDTO> getAllTypePartenaires();
    TypePartenaireRespDTO getTypePartenaireById(Long idTypePartenaire);
    TypePartenaireRespDTO updateTypePartenaire(Long id ,TypePartenaireCreateDTO typePartenaireDetails);
    void deleteTypePartenaire(Long idTypePartenaire);


    /// ADRESS  SERVICE CRUD
    public Adress addNewAdress(Adress adress);
    public List<Adress> getAllAdresses();
    public Adress getAdressById(Long idAdress);
    public Adress updateAdress(Adress adressDetails);
    public void deleteAdress(Long idAdress);


}
