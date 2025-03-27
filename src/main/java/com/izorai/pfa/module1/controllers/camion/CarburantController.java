package com.izorai.pfa.module1.controllers.camion;

import com.izorai.pfa.module1.DTO.camion.carburant.CarburantDTO;
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
    public ResponseEntity<CarburantDTO> createCarburant(@RequestBody CarburantDTO carburantDTO) {
        CarburantDTO createdCarburant = carburantService.createCarburant(carburantDTO);
        return new ResponseEntity<>(createdCarburant, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CarburantDTO>> getAllCarburants() {
        List<CarburantDTO> carburants = carburantService.getAllCarburants();
        return new ResponseEntity<>(carburants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarburantDTO> getCarburantById(@PathVariable Long id) {
        Optional<CarburantDTO> carburant = carburantService.getCarburantById(id);
        return carburant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarburantDTO> updateCarburant(@PathVariable Long id, @RequestBody CarburantDTO carburantDTO) {
        CarburantDTO updatedCarburant = carburantService.updateCarburant(id, carburantDTO);
        return new ResponseEntity<>(updatedCarburant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarburant(@PathVariable Long id) {
        carburantService.deleteCarburant(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Récupérer les carburants associés à un camion par son immatriculation
    @GetMapping("/camion/{immatriculationCamion}")
    public ResponseEntity<List<CarburantDTO>> getCarburantsByCamion(@PathVariable String immatriculationCamion) {
        List<CarburantDTO> carburants = carburantService.getCarburantsByCamion(immatriculationCamion);
        return new ResponseEntity<>(carburants, HttpStatus.OK);
    }

    // Récupérer les carburants dans un intervalle de dates
        @GetMapping("/date-range")
        public ResponseEntity<List<CarburantDTO>> getCarburantsByDateRange(@RequestParam LocalDate debut, @RequestParam LocalDate fin) {
            List<CarburantDTO> carburants = carburantService.getCarburantsByDateRange(debut, fin);
            return new ResponseEntity<>(carburants, HttpStatus.OK);
        }

    // Récupérer la consommation moyenne de carburant d'un camion (en litres/100 km)
    @GetMapping("/consommation-moyenne/{immatriculationCamion}")
    public ResponseEntity<Double> getConsommationMoyenneByCamion(@PathVariable String immatriculationCamion) {
        double consommationMoyenne = carburantService.getConsommationMoyenneByCamion(immatriculationCamion);
        return new ResponseEntity<>(consommationMoyenne, HttpStatus.OK);
    }

    // Récupérer le coût total de carburant d'un camion
    @GetMapping("/cout-total/{immatriculationCamion}")
    public ResponseEntity<Double> getCoutTotalCarburant() {
        double coutTotal = carburantService.getCoutTotalCarburant();
        return new ResponseEntity<>(coutTotal, HttpStatus.OK);
    }
}
