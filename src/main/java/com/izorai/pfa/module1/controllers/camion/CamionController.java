package com.izorai.pfa.module1.controllers.camion;

import com.izorai.pfa.module1.DTO.camion.camion.CamionDTO;
import com.izorai.pfa.module1.DTO.camion.camion.CamionRespDto;
import com.izorai.pfa.module1.services.camion.camion.CamionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/camions")
public class CamionController {

    private final CamionService camionService;


    public CamionController(CamionService camionService) {
        this.camionService = camionService;
    }

    // Camion Endpoints

    @PostMapping
    public ResponseEntity<CamionDTO> addNewCamion(@RequestBody CamionDTO camionDTO) {
        CamionDTO newCamion = camionService.addNewCamion(camionDTO);
        return new ResponseEntity<>(newCamion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CamionDTO>> getAllCamions() {
        List<CamionDTO> camions = camionService.getAllCamions();
        return new ResponseEntity<>(camions, HttpStatus.OK);
    }

    @GetMapping("/{immatriculation}")
    public ResponseEntity<CamionRespDto> getCamionById(@PathVariable String immatriculation) {
        Optional<CamionRespDto> camionDTO = camionService.getCamionById(immatriculation);
        return camionDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{immatriculation}")
    public ResponseEntity<CamionDTO> updateCamion(@PathVariable String immatriculation,
                                                  @RequestBody CamionDTO camionDTO) {
        CamionDTO updatedCamion = camionService.updateCamion(immatriculation, camionDTO);
        return new ResponseEntity<>(updatedCamion, HttpStatus.OK);
    }

    @DeleteMapping("/{immatriculation}")
    public ResponseEntity<Void> deleteCamion(@PathVariable String immatriculation) {
        camionService.deleteCamion(immatriculation);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
