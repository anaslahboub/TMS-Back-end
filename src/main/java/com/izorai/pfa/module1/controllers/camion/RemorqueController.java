package com.izorai.pfa.module1.controllers.camion;

import com.izorai.pfa.module1.DTO.camion.remorque.RemorqueDTO;
import com.izorai.pfa.module1.entities.camion.Remorque;
import com.izorai.pfa.module1.services.camion.remorque.RemorqueService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/remorques")
public class RemorqueController {

    private final RemorqueService remorqueService;

    @Autowired
    public RemorqueController(RemorqueService remorqueService) {
        this.remorqueService = remorqueService;
    }

    @PostMapping
    public ResponseEntity<RemorqueDTO> addNewRemorque(@RequestBody RemorqueDTO remorqueDTO) {
        RemorqueDTO createdRemorque = remorqueService.addNewRemorque(remorqueDTO);
        return new ResponseEntity<>(createdRemorque, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RemorqueDTO>> getAllRemorques() {
        List<RemorqueDTO> remorques = remorqueService.getAllRemorques();
        return new ResponseEntity<>(remorques, HttpStatus.OK);
    }

    @GetMapping("/{immatriculation}")
    public ResponseEntity<RemorqueDTO> getRemorqueById(@PathVariable Long immatriculation) {
        Optional<RemorqueDTO> remorque = remorqueService.getRemorqueById(immatriculation);
        return remorque.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{immatriculation}")
    public ResponseEntity<RemorqueDTO> updateRemorque(@PathVariable Long immatriculation, @RequestBody RemorqueDTO remorqueDTO) {
        RemorqueDTO updatedRemorque = remorqueService.updateRemorque(immatriculation, remorqueDTO);
        return new ResponseEntity<>(updatedRemorque, HttpStatus.OK);
    }

    @DeleteMapping("/{immatriculation}")
    public ResponseEntity<Void> deleteRemorque(@PathVariable Long immatriculation) {
        remorqueService.deleteRemorque(immatriculation);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Remorque>> getRemorquesDisponibles() {
        List<Remorque> remorques = remorqueService.getRemorquesDisponibles();
        return ResponseEntity.ok(remorques);
    }
}
