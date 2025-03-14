package com.izorai.pfa.module1.controllers.camion;

import com.izorai.pfa.module1.DTO.camion.remorque.RemorqueCreateDto;
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
    public ResponseEntity<RemorqueDTO> addNewRemorque(@RequestBody RemorqueCreateDto remorqueDTO) {
        RemorqueDTO createdRemorque = remorqueService.addNewRemorque(remorqueDTO);
        return new ResponseEntity<>(createdRemorque, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RemorqueDTO>> getAllRemorques() {
        List<RemorqueDTO> remorques = remorqueService.getAllRemorques();
        return new ResponseEntity<>(remorques, HttpStatus.OK);
    }

    @GetMapping("/{idRemorque}")
    public ResponseEntity<RemorqueDTO> getRemorqueById(@PathVariable Long idRemorque) {
        Optional<RemorqueDTO> remorque = remorqueService.getRemorqueById(idRemorque);
        return remorque.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{idRemorque}")
    public ResponseEntity<RemorqueDTO> updateRemorque(@PathVariable Long idRemorque, @RequestBody RemorqueDTO remorqueDTO) {
        RemorqueDTO updatedRemorque = remorqueService.updateRemorque(idRemorque, remorqueDTO);
        return new ResponseEntity<>(updatedRemorque, HttpStatus.OK);
    }

    @DeleteMapping("/{idRemorque}")
    public ResponseEntity<Void> deleteRemorque(@PathVariable Long idRemorque) {
        remorqueService.deleteRemorque(idRemorque);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Remorque>> getRemorquesDisponibles() {
        List<Remorque> remorques = remorqueService.getRemorquesDisponibles();
        return ResponseEntity.ok(remorques);
    }
}
