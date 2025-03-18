package com.izorai.pfa.module1.controllers.partenaire;

import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.paretenaire.PartenaireCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.paretenaire.PartenaireRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.typePartenaire.TypePartenaireCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.typePartenaire.TypePartenaireRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.services.partenaire.partenaire.PartenaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partenaires")
public class PartenaireController {

    private final PartenaireService partenaireService;


    public PartenaireController(PartenaireService partenaireService) {
        this.partenaireService = partenaireService;
    }


    @PostMapping
    public ResponseEntity<PartenaireCreateDTO> createPartenaire(@RequestBody PartenaireCreateDTO dto) {
        return ResponseEntity.ok(partenaireService.createPartenaire(dto));
    }

    @GetMapping
    public ResponseEntity<List<PartenaireRespDTO>> listPartenaires() {
        return ResponseEntity.ok(partenaireService.getAllPartenaires());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartenaireRespDTO> getPartenaireById(@PathVariable Long id) {
        return ResponseEntity.ok(partenaireService.getPartenaireById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartenaire(@PathVariable Long id) {
        partenaireService.deletePartenaire(id);
        return ResponseEntity.noContent().build(); // Retourne 204 No Content
    }




    // Gestion des Personnes Physiques





}
