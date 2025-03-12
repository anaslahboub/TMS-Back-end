package com.izorai.pfa.module1.services.camion.carburant;

import com.izorai.pfa.module1.DTO.camion.carburant.CarburantDTO;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Carburant;
import com.izorai.pfa.module1.entities.enumerations.TypeCarburant;
import com.izorai.pfa.module1.mappers.camion.CarburantMapper;
import com.izorai.pfa.module1.repository.camion.CarburantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarburantServiceImplTest {

//
//    @Mock
//    CarburantRepository carburantRepository;
//    @Mock
//    CarburantMapper carburantMapper;
//
//    @InjectMocks
//    CarburantServiceImpl carburantService;
//
//    Carburant carburant;
//    CarburantDTO carburantDTO;
//    List<CarburantDTO> carburantDTOList;
//
//    @BeforeEach
//    void setUp() {
//        Camion camion = new Camion();
//        carburantDTO = new CarburantDTO(1L, LocalDate.of(2001,1,21),500,200,10000, TypeCarburant.DIESEL,camion);
//
//        carburant = new Carburant();
//        carburantDTOList = new LinkedList<>();
//    }
//
//    @AfterEach
//    void tearDown() {
//        System.out.println("test service est terminer ");
//    }
//
//    @Test
//    void createCarburant() {
//        when(carburantMapper.fromCarburantDTO(carburantDTO)).thenReturn(carburant);
//        when(carburantRepository.save(carburant)).thenReturn(carburant);
//        when(carburantMapper.toCarburantDTO(carburant)).thenReturn(carburantDTO);
//
//        CarburantDTO result = carburantService.createCarburant(carburantDTO);
//
//        assertNotNull(result,"l object returner est null");
//        assertEquals(result,carburantDTO,"les deux objects ne sont pas identiques");
//
//    }
//
//    @Test
//    void getAllCarburants() {
//        when(carburantRepository.findAll()).thenReturn(List.of(carburant));
//        when(carburantMapper.toCarburantDTO(carburant)).thenReturn(carburantDTO);
//        List<CarburantDTO> result = carburantService.getAllCarburants();
//        assertNotNull(result,"l object returner est null");
//        assertFalse(result.isEmpty(),"l object returner est vide");
//        assertEquals(carburantDTO.id(),result.get(0).id(),"l object returner est vide");
//    }
//
//    @Test
//    void getCarburantById() {
//        when(carburantRepository.findById(1L).get()).thenReturn(carburant);
//        when(carburantMapper.toCarburantDTO(carburant)).thenReturn(carburantDTO);
//        CarburantDTO result = carburantService.getCarburantById(1L).get();
//        assertNotNull(result,"l object returner est null");
//
//        assertEquals(carburantDTO,result,"l object returner nest pas identique ");
//    }
//
//    @Test
//    void updateCarburant() {
//    }
//
//    @Test
//    void deleteCarburant() {
//    }
}