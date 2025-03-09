package com.izorai.pfa.module1.controllers.partenaire;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.services.partenaire.adress.AdressService;
import com.izorai.pfa.module1.services.partenaire.partenaire.PartenaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adresses")
public class AdressController {

    private final AdressService adressService;


    public AdressController(AdressService adressService) {
        this.adressService = adressService;}


        @PostMapping
        public ResponseEntity<Adress> addNewAdress(@RequestBody Adress adress) {
            Adress newAdress = adressService.addNewAdress(adress);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAdress);
        }

        // Récupérer toutes les adresses
        @GetMapping
        public ResponseEntity<List<Adress>> getAllAdresses() {
            List<Adress> adresses = adressService.getAllAdresses();
            return ResponseEntity.ok(adresses);
        }

        // Récupérer une adresse par ID
        @GetMapping("/{id}")
        public ResponseEntity<Adress> getAdressById(@PathVariable Long id) {
            Adress adress = adressService.getAdressById(id);
            return adress != null ? ResponseEntity.ok(adress) : ResponseEntity.notFound().build();
        }

        // Mettre à jour une adresse
        @PutMapping("/{id}")
        public ResponseEntity<Adress> updateAdress(@PathVariable Long id, @RequestBody Adress adressDetails) {
            Adress updatedAdress = adressService.updateAdress( adressDetails);
            return updatedAdress != null ? ResponseEntity.ok(updatedAdress) : ResponseEntity.notFound().build();
        }

        // Supprimer une adresse
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteAdress(@PathVariable Long id) {
            adressService.deleteAdress(id);
            return ResponseEntity.noContent().build();
        }


}
