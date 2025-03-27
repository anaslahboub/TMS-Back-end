package com.izorai.pfa.module1.DTO.partenaire.chaufeur;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChaufeurCreateDTO{
        String nom;
        String email;
        String telephone;
        String cni;
        String prenom;
        String cnss;
        LocalDate dateRecrutement;
        LocalDate dateExpirationPermis;
        String disponibilite;
        List<Adress> adresses;

        private byte[] photoPermisRecto;
        private byte[] photoPermisVerso;


}
