package com.izorai.pfa.module1.controllers.camion;

import com.izorai.pfa.module1.DTO.camion.entretien.EntretienDTO;
import com.izorai.pfa.module1.services.camion.entretien.EntrtienService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entretiens")
public class EntretienController {

    private final EntrtienService entretienService;

    @Autowired
    public EntretienController(EntrtienService entretienService) {
        this.entretienService = entretienService;
    }

    @PostMapping
    public ResponseEntity<EntretienDTO> createEntretien(@RequestBody EntretienDTO entretienDTO) {
        EntretienDTO createdEntretien = entretienService.createEntretien(entretienDTO);
        return new ResponseEntity<>(createdEntretien, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EntretienDTO>> getAllEntretiens() {
        List<EntretienDTO> entretiens = entretienService.getAllEntretiens();
        return new ResponseEntity<>(entretiens, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntretienDTO> getEntretienById(@PathVariable Long id) {
        Optional<EntretienDTO> entretien = entretienService.getEntretienById(id);
        return entretien.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntretienDTO> updateEntretien(@PathVariable Long id, @RequestBody EntretienDTO entretienDTO) {
        EntretienDTO updatedEntretien = entretienService.updateEntretien(id, entretienDTO);
        return new ResponseEntity<>(updatedEntretien, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntretien(@PathVariable Long id) {
        entretienService.deleteEntretien(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/camion/{immatriculation}")
    public ResponseEntity<List<EntretienDTO>> getEntretiensByCamion(@PathVariable String immatriculation) {
        List<EntretienDTO> entretiens = entretienService.getEntretiensByCamion(immatriculation);
        return new ResponseEntity<>(entretiens, HttpStatus.OK);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<EntretienDTO>> getEntretiensByDateRange(
            @RequestParam("debut") String debut,
            @RequestParam("fin") String fin) {
        LocalDate startDate = LocalDate.parse(debut);
        LocalDate endDate = LocalDate.parse(fin);
        List<EntretienDTO> entretiens = entretienService.getEntretiensByDateRange(startDate, endDate);
        return new ResponseEntity<>(entretiens, HttpStatus.OK);
    }

    @GetMapping("/type/{typeEntretien}")
    public ResponseEntity<List<EntretienDTO>> getEntretiensByType(@PathVariable String typeEntretien) {
        List<EntretienDTO> entretiens = entretienService.getEntretiensByType(typeEntretien);
        return new ResponseEntity<>(entretiens, HttpStatus.OK);
    }

    @GetMapping("/cout/camion/{immatriculation}")
    public ResponseEntity<Double> getCoutTotalEntretiensByCamion(@PathVariable String immatriculation) {
        double coutTotal = entretienService.getCoutTotalEntretiensByCamion(immatriculation);
        return new ResponseEntity<>(coutTotal, HttpStatus.OK);
    }

    @GetMapping("/cout/periode")
    public ResponseEntity<Double> getCoutTotalEntretiensByPeriode(
            @RequestParam("debut") String debut,
            @RequestParam("fin") String fin) {
        LocalDate startDate = LocalDate.parse(debut);
        LocalDate endDate = LocalDate.parse(fin);
        double coutTotal = entretienService.getCoutTotalEntretiensByPeriode(startDate, endDate);
        return new ResponseEntity<>(coutTotal, HttpStatus.OK);
    }

    @GetMapping("/cout/camion/{immatriculation}/calculate")
    public ResponseEntity<Double> calculateCoutEntretiens(@PathVariable String immatriculation) {
        double coutTotal = entretienService.calculateCoutEntretiens(immatriculation);
        return new ResponseEntity<>(coutTotal, HttpStatus.OK);
    }
}
