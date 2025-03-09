package com.izorai.pfa.module1.controllers.camion;

import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;
import com.izorai.pfa.module1.services.camion.cartegrise.CarteGriseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/cartegrises")
public class CarteGriseController {
    private final CarteGriseService catreGriseService;

    public CarteGriseController(CarteGriseService catreGriseService) {
        this.catreGriseService = catreGriseService;
    }

    @PostMapping
    public ResponseEntity<CarteGriseDTO> addNewCarteGrise(@RequestBody CarteGriseDTO carteGrise) {
        CarteGriseDTO newCarteGrise = catreGriseService.addNewCarteGrise(carteGrise);
        return new ResponseEntity<>(newCarteGrise, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CarteGriseDTO>> getAllCarteGrises() {
        List<CarteGriseDTO> carteGrises = catreGriseService.getAllCarteGrises();
        return new ResponseEntity<>(carteGrises, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarteGriseDTO> getCarteGriseById(@PathVariable Long id) {
        Optional<CarteGriseDTO> carteGriseDTO = catreGriseService.getCarteGriseById(id);
        return carteGriseDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarteGriseDTO> updateCarteGrise(@PathVariable Long id,
                                                          @RequestBody CarteGriseDTO carteGriseDetails) {
        CarteGriseDTO updatedCarteGrise = catreGriseService.updateCarteGrise(id, carteGriseDetails);
        return new ResponseEntity<>(updatedCarteGrise, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarteGrise(@PathVariable Long id) {
        catreGriseService.deleteCarteGrise(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
