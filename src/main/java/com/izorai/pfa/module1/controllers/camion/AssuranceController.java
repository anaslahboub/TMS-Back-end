package com.izorai.pfa.module1.controllers.camion;

import com.izorai.pfa.module1.DTO.camion.remorque.assurance.AssuranceDTO;
import com.izorai.pfa.module1.services.camion.assurance.AssuranceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/assurances")
public class AssuranceController {

    private final AssuranceService assuranceService;

    public AssuranceController(AssuranceService assuranceService) {
        this.assuranceService = assuranceService;
    }

    @PostMapping
    public ResponseEntity<AssuranceDTO> addNewAssurance(@RequestBody AssuranceDTO assurance) {
        AssuranceDTO newAssurance = assuranceService.addNewAssurance(assurance);
        return new ResponseEntity<>(newAssurance, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AssuranceDTO>> getAllAssurances() {
        List<AssuranceDTO> assurances = assuranceService.getAllAssurances();
        return new ResponseEntity<>(assurances, HttpStatus.OK);
    }

    @GetMapping("/{numeroContrat}")
    public ResponseEntity<AssuranceDTO> getAssuranceById(@PathVariable Long numeroContrat) {
        try {
            // Appeler le service pour récupérer l'assurance
            AssuranceDTO assuranceDTO = assuranceService.getAssuranceById(numeroContrat);
            // Si l'assurance existe, retourner OK avec l'objet
            return ResponseEntity.ok(assuranceDTO);
        } catch (RuntimeException e) {
            // Si l'assurance n'existe pas, retourner un statut 404 NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PutMapping("/{numeroContrat}")
    public ResponseEntity<AssuranceDTO> updateAssurance(@PathVariable Long numeroContrat,
                                                        @RequestBody AssuranceDTO assuranceDetails) {
        AssuranceDTO updatedAssurance = assuranceService.updateAssurance(numeroContrat, assuranceDetails);
        return new ResponseEntity<>(updatedAssurance, HttpStatus.OK);
    }

    @DeleteMapping("/{numeroContrat}")
    public ResponseEntity<Void> deleteAssurance(@PathVariable Long numeroContrat) {
        assuranceService.deleteAssurance(numeroContrat);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Endpoint pour récupérer l'assurance associée à un camion en fonction de son immatriculation
    @GetMapping("/camion/{immatriculation}")
    public ResponseEntity<AssuranceDTO> getAssuranceByCamion(@PathVariable String immatriculation) {
        AssuranceDTO assuranceDTO = assuranceService.getAssuranceByCamion(immatriculation);
        return ResponseEntity.ok(assuranceDTO);
    }

    // Endpoint pour renouveler une assurance
    @PutMapping("/{numeroContrat}/renouveler")
    public ResponseEntity<AssuranceDTO> renouvelerAssurance(@PathVariable Long numeroContrat,
                                                            @RequestParam("nouvelleDate") String nouvelleDate) {
        LocalDate date = LocalDate.parse(nouvelleDate);
        AssuranceDTO renewedAssurance = assuranceService.renouvelerAssurance(numeroContrat, date);
        return ResponseEntity.ok(renewedAssurance);
    }

    // Endpoint pour récupérer le total des primes annuelles des assurances actives
    @GetMapping("/total-primes-annuelles")
    public ResponseEntity<Double> getTotalPrimesAnnuelles() {
        double totalPrimes = assuranceService.getTotalPrimesAnnuelles();
        return ResponseEntity.ok(totalPrimes);
    }

    // Endpoint pour vérifier l'expiration des assurances et envoyer des alertes
    @GetMapping("/check-expiration")
    public ResponseEntity<Void> checkExpirationAssurances() {
        assuranceService.checkExpirationAssurances();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Endpoint pour récupérer les assurances expirant dans les 30 jours
    @GetMapping("/expirant-dans-30-jours")
    public ResponseEntity<List<AssuranceDTO>> getAssurancesExpirantDans30Jours() {
        List<AssuranceDTO> assurancesExpirant = assuranceService.getAssurancesExpirantDans30Jours();
        return ResponseEntity.ok(assurancesExpirant);
    }
}
