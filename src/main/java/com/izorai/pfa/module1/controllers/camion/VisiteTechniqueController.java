package com.izorai.pfa.module1.controllers.camion;

import com.izorai.pfa.module1.DTO.camion.visiteTechnique.VisiteTechniqueDto;
import com.izorai.pfa.module1.entities.camion.VisiteTechnique;
import com.izorai.pfa.module1.mappers.camion.VisiteTechniqueMapper;
import com.izorai.pfa.module1.services.camion.visiteTechnique.VisiteTechniqueService;
import com.izorai.pfa.module1.services.fileStorage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/visites-techniques")
@RequiredArgsConstructor
public class VisiteTechniqueController {

    private final VisiteTechniqueService visiteTechniqueService;
    private final VisiteTechniqueMapper visiteTechniqueMapper;
    private final FileStorageService fileStorageService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VisiteTechniqueDto> createVisiteTechnique(
            @RequestPart("visite") VisiteTechnique visiteTechnique,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        String filename = fileStorageService.storeFile(file,"visites");
        visiteTechnique.setDocumentUrl(filename);
        VisiteTechniqueDto savedVisite = visiteTechniqueService.save(visiteTechnique);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVisite);
    }
    @GetMapping("/{id}")
    public ResponseEntity<VisiteTechniqueDto> getVisiteTechniqueById(@PathVariable Long id) {
        VisiteTechniqueDto visite = visiteTechniqueService.getById(id);
        return ResponseEntity.ok(visite);
    }

    @GetMapping("/{id}/document")
    public ResponseEntity<Resource> getDocument(@PathVariable Long id) throws IOException {
        VisiteTechniqueDto visite = visiteTechniqueService.getById(id);

        if (visite.getDocumentUrl() == null || visite.getDocumentUrl().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Path filePath = Paths.get("uploads").resolve(visite.getDocumentUrl()).normalize();
        Resource resource = new InputStreamResource(Files.newInputStream(filePath));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + filePath.getFileName() + "\"")
                .body(resource);
    }

//    @GetMapping(value = "/{id}", produces = MediaType.MULTIPART_MIXED_VALUE)
//    public ResponseEntity<MultiValueMap<String, Object>> getVisiteTechniqueWithDocument(@PathVariable Long id) throws IOException {
//        VisiteTechniqueDto visite = visiteTechniqueService.getById(id);
//        Path filePath = Paths.get("uploads").resolve(visite.getDocumentUrl());
//
//        // First part - JSON data
//        HttpHeaders jsonHeaders = new HttpHeaders();
//        jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        // Second part - File data
//        Resource fileResource = new InputStreamResource(Files.newInputStream(filePath));
//        HttpHeaders fileHeaders = new HttpHeaders();
//        fileHeaders.setContentType(MediaType.parseMediaType(Files.probeContentType(filePath)));
//        fileHeaders.setContentDispositionFormData("document", filePath.getFileName().toString());
//
//        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
//        parts.add("visite", new HttpEntity<>(visite, jsonHeaders));
//        parts.add("document", new HttpEntity<>(fileResource, fileHeaders));
//
//        return new ResponseEntity<>(parts, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<VisiteTechniqueDto>> getAllVisitesTechniques() {
        List<VisiteTechniqueDto> visites = visiteTechniqueService.getAll();
        return ResponseEntity.ok(visites);
    }

    @GetMapping("/camion/{immatriculation}")
    public ResponseEntity<List<VisiteTechniqueDto>> getVisitesByCamion(@PathVariable String immatriculation) {
        List<VisiteTechniqueDto> visites = visiteTechniqueService.getByCamion(immatriculation);
        return ResponseEntity.ok(visites);
    }

    @GetMapping("/expirant")
    public ResponseEntity<List<VisiteTechniqueDto>> getVisitesExpirantDans30Jours() {
        List<VisiteTechniqueDto> visites = visiteTechniqueService.getVisitesTechniquesExpirantDans30Jours().stream().map(visiteTechniqueMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(visites);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VisiteTechniqueDto> updateVisiteTechnique(
            @PathVariable Long id,
            @RequestPart("visite") VisiteTechnique visiteTechnique,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException {


        String oldFilePath = visiteTechnique.getDocumentUrl();

        if (file != null && !file.isEmpty()) {
            String newFilePath = fileStorageService.updateFile(file, oldFilePath, "visites");
            visiteTechnique.setDocumentUrl(newFilePath);
        } else {
            // Pas de nouveau fichier → garder l'ancien
            visiteTechnique.setDocumentUrl(oldFilePath);
        }

        // 3. Mise à jour de l'entité
        VisiteTechniqueDto updatedVisite = visiteTechniqueService.update(id, visiteTechnique);
        return ResponseEntity.ok(updatedVisite);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisiteTechnique(@PathVariable Long id) {
        visiteTechniqueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}