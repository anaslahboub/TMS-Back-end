package com.izorai.pfa.module1.controllers.camion;

import com.izorai.pfa.module1.DTO.camion.carburant.CarburantDTO;
import com.izorai.pfa.module1.DTO.camion.carburant.CarburantRespDto;
import com.izorai.pfa.module1.services.camion.carburant.CarburantService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carburants")
public class CarburantController {

    private final CarburantService carburantService;

    @Autowired
    public CarburantController(CarburantService carburantService) {
        this.carburantService = carburantService;
    }

    @PostMapping
    public ResponseEntity<CarburantRespDto> createCarburant(@RequestBody CarburantDTO carburantDTO) {
        CarburantRespDto createdCarburant = carburantService.createCarburant(carburantDTO);
        return new ResponseEntity<>(createdCarburant, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CarburantRespDto>> getAllCarburants() {
        List<CarburantRespDto> carburants = carburantService.getAllCarburants();
        return new ResponseEntity<>(carburants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarburantRespDto> getCarburantById(@PathVariable Long id) {
        Optional<CarburantRespDto> carburant = carburantService.getCarburantById(id);
        return carburant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarburantRespDto> updateCarburant(@PathVariable Long id, @RequestBody CarburantDTO carburantDTO) {
        CarburantRespDto updatedCarburant = carburantService.updateCarburant(id, carburantDTO);
        return new ResponseEntity<>(updatedCarburant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarburant(@PathVariable Long id) {
        carburantService.deleteCarburant(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Récupérer les carburants associés à un camion par son immatriculation
    @GetMapping("/camion/{immatriculationCamion}")
    public ResponseEntity<List<CarburantRespDto>> getCarburantsByCamion(@PathVariable String immatriculationCamion) {
        List<CarburantRespDto> carburants = carburantService.getCarburantsByCamion(immatriculationCamion);
        return new ResponseEntity<>(carburants, HttpStatus.OK);
    }

    // Consommation moyenne d'un camion
    @GetMapping("/consommation/{immatriculationCamion}")
    public ResponseEntity<Double> getConsommationMoyenneByCamion(@PathVariable String immatriculationCamion) {
        double consommationMoyenne = carburantService.getConsommationMoyenneByCamion(immatriculationCamion);
        return new ResponseEntity<>(consommationMoyenne, HttpStatus.OK);
    }

    // Coût total du carburant
    @GetMapping("/cout-total")
    public ResponseEntity<Double> getCoutTotalCarburant() {
        double coutTotal = carburantService.getCoutTotalCarburant();
        return new ResponseEntity<>(coutTotal, HttpStatus.OK);
    }

    // Distance totale parcourue
    @GetMapping("/distance-total")
    public ResponseEntity<Double> getDistanceTotalParcourue() {
        double distanceTotal = carburantService.getDistanceTotalParcourue();
        return new ResponseEntity<>(distanceTotal, HttpStatus.OK);
    }

    // Quantité totale de carburant consommée
    @GetMapping("/quantite-total")
    public ResponseEntity<Double> getQuantityTotal() {
        double quantiteTotal = carburantService.getQuantityTotal();
        return new ResponseEntity<>(quantiteTotal, HttpStatus.OK);
    }

    // Prix moyen du carburant
    @GetMapping("/prix-moyen")
    public ResponseEntity<Double> getPrixMoyenne() {
        double prixMoyen = carburantService.getPrixMoyenne();
        return new ResponseEntity<>(prixMoyen, HttpStatus.OK);
    }

    // Taux de consommation moyenne
    @GetMapping("/taux-consommation")
    public ResponseEntity<Double> getTauxConsommationMoyenne() {
        double tauxConsommation = carburantService.getTauxConsommationMoyenne();
        return new ResponseEntity<>(tauxConsommation, HttpStatus.OK);
    }

}
