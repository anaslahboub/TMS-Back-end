package com.izorai.pfa.module1.services.camion.station;

import com.izorai.pfa.module1.entities.camion.Station;
import com.izorai.pfa.module1.repository.camion.StationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;


    @Override
    public Station create(Station stationDto) {
        return stationRepository.save(stationDto);
    }

    @Override
    public Station getStationById(Long id) {
        return stationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    @Transactional
    public Station update(Long id ,Station stationDto) {
            Station station = stationRepository.findById(id).orElse(null);
            station.setName(stationDto.getName());

        return station;
    }


    @Override
    public void delete(Long id) {
            stationRepository.deleteById(id);
    }
}
