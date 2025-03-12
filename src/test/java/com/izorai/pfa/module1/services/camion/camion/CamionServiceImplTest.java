package com.izorai.pfa.module1.services.camion.camion;

import com.izorai.pfa.module1.DTO.camion.assurance.AssuranceDTO;
import com.izorai.pfa.module1.DTO.camion.camion.CamionDTO;
import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;
import com.izorai.pfa.module1.entities.camion.*;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.mappers.camion.CamionMapper;
import com.izorai.pfa.module1.repository.camion.CamionRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CamionServiceImplTest {

//    @Mock
//    private CamionRepository camionRepository;
//
//    @Mock
//    private CamionMapper camionMapper;
//
//    @InjectMocks
//    private CamionServiceImpl camionService;
//
//    private CamionDTO camionDTO;
//    private Camion camion;
//
//    @BeforeEach
//    void setUp() {
//        System.out.println("Setting up test data...");
//
//        // Initialize test data
//        CarteGriseDTO carteGriseDTO = new CarteGriseDTO(
//                1L, LocalDate.of(2020, 5, 15), "Mercedes", "Camion",
//                123456789L, "Blanc", 2, "10 CV", "Diesel",
//                "Entreprise XYZ", 3500, 7500, LocalDate.of(2020, 6, 1),
//                new Adress(1L, "Domicile", "123 Rue des Transports", "Casablanca", "Maroc", "20000")
//        );
//
//        AssuranceDTO assuranceDTO = new AssuranceDTO(
//                987654321L, "AXA Assurances", "Tous risques",
//                50000, LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1),
//                3000, 11223344L
//        );
//
//        camionDTO = new CamionDTO("azerty", carteGriseDTO, assuranceDTO);
//
//        CarteGrise carteGrise = new CarteGrise();
//        Assurance assurance = new Assurance();
//        Entretien entretien = new Entretien();
//        Carburant carburant = new Carburant();
//        List<Entretien> entretienList = new ArrayList<>();
//        List<Carburant> carburantList = new ArrayList<>();
//        entretienList.add(entretien);
//        carburantList.add(carburant);
//        camion = new Camion("azerty", "aa",1000,100,true,carteGrise, assurance,entretienList,carburantList);
//    }
//    @AfterEach
//    void shutDown() {
//        System.out.println("Shutting down test data...");
//    }
//
//    @Test
//    public void testAddNewCamion() {
//        // Mock the mapper and repository
//        when(camionMapper.fromCamionDTO(camionDTO)).thenReturn(camion);
//        when(camionRepository.save(camion)).thenReturn(camion);
//        when(camionMapper.toCamionDto(camion)).thenReturn(camionDTO);
//
//        // Call the service method
//        CamionDTO result = camionService.addNewCamion(camionDTO);
//
//        // Verify the result
//        assertNotNull(result, "Le résultat retourné par le service addNewCamion est nul");
//        assertEquals(camionDTO.immatriculation(), result.immatriculation(),
//                String.format("Les immatriculations ne correspondent pas. Attendu : %s, mais obtenu : %s",
//                        camionDTO.immatriculation(), result.immatriculation()));
//
//
//    }
//
//    @Test
//    public void testGetAllCamions() {
//        // Mock the repository and mapper
//        when(camionRepository.findAll()).thenReturn(List.of(camion));
//        when(camionMapper.toCamionDto(camion)).thenReturn(camionDTO);
//
//        // Call the service method
//        List<CamionDTO> result = camionService.getAllCamions();
//
//        // Verify the result
//        assertNotNull(result, "La liste des camions retournée est nulle");
//        assertFalse(result.isEmpty(), "La liste des camions est vide");
//        assertEquals(1, result.size(), "La taille de la liste des camions est incorrecte");
//        assertEquals(camionDTO.immatriculation(), result.get(0).immatriculation(),
//                "L'immatriculation du camion ne correspond pas");
//    }
//
//    @Test
//    public void testGetCamionById() {
//        // Mock the repository and mapper
//        when(camionRepository.findByImmatriculation("azerty")).thenReturn(Optional.of(camion));
//        when(camionMapper.toCamionDto(camion)).thenReturn(camionDTO);
//
//        // Call the service method
//        Optional<CamionDTO> result = camionService.getCamionById("azerty");
//
//        // Verify the result
//        assertTrue(result.isPresent(), "Le camion n'a pas été trouvé");
//        assertEquals(camionDTO.immatriculation(), result.get().immatriculation(),
//                "L'immatriculation du camion ne correspond pas");
//    }
//
//    @Test
//    public void testUpdateCamion() {
//        // Mock the repository and mapper
//        when(camionRepository.findByImmatriculation("azerty")).thenReturn(Optional.of(camion));
//        when(camionMapper.fromCamionDTO(camionDTO)).thenReturn(camion);
//        when(camionRepository.save(camion)).thenReturn(camion);
//        when(camionMapper.toCamionDto(camion)).thenReturn(camionDTO);
//
//        // Call the service method
//        CamionDTO result = camionService.updateCamion("azerty", camionDTO);
//
//        // Verify the result
//        assertNotNull(result, "Le résultat de la mise à jour est nul");
//        assertEquals(camionDTO.immatriculation(), result.immatriculation(),
//                "L'immatriculation du camion ne correspond pas");
//
//    }
//
//    @Test
//    public void testDeleteCamion() {
//        // Mock the repository
//        when(camionRepository.existsByImmatriculation("azerty")).thenReturn(true);
//        doNothing().when(camionRepository).deleteByImmatriculation("azerty");
//
//        // Call the service method
//        camionService.deleteCamion("azerty");
//
//
//    }
//
//    @Test
//    public void testDeleteCamion_NotFound() {
//        // Mock the repository to return false (camion not found)
//        when(camionRepository.existsByImmatriculation("azerty")).thenReturn(false);
//
//        // Call the service method and expect an exception
//        assertThrows(RuntimeException.class, () -> camionService.deleteCamion("azerty"),
//                "Une exception aurait dû être levée car le camion n'existe pas");
//
//    }
}