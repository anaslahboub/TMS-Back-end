package com.izorai.pfa.module1.controllers.camion;

import com.izorai.pfa.module1.entities.camion.Station;
import com.izorai.pfa.module1.services.camion.station.StationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stations")
@AllArgsConstructor
public class StationController {
    private final StationService stationService;



    // Add a new TypeCarburant
    @PostMapping
    public ResponseEntity<Station> addNewStation(@RequestBody Station station) {
        Station newTypeCarburant = stationService.create(station);
        return new ResponseEntity<>(newTypeCarburant, HttpStatus.CREATED);
    }

    // Get all TypeCarburants
    @GetMapping
    public ResponseEntity<List<Station>> getAllTypeStations() {
        List<Station> station = stationService.getAllStations();
        return new ResponseEntity<>(station, HttpStatus.OK);
    }

    // Get a TypeCarburant by ID
    @GetMapping("/{id}")
    public ResponseEntity<Station> getTypeStationById(@PathVariable Long id) {
        Station station = stationService.getStationById(id);
        return ResponseEntity.ok(station);
    }

    // Update a TypeCarburant
    @PutMapping("/{id}")
    public ResponseEntity<Station> updateTypeStation(@PathVariable Long id,
                                                     @RequestBody Station station1) {
        Station station = stationService.update(id, station1);
        return new ResponseEntity<>(station, HttpStatus.OK);
    }

    // Delete a TypeCarburant by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeStation(@PathVariable Long id) {
        stationService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
