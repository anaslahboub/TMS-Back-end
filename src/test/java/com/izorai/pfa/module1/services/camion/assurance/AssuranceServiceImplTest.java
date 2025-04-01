package com.izorai.pfa.module1.services.camion.assurance;

import com.izorai.pfa.module1.DTO.camion.remorque.assurance.AssuranceDTO;
import com.izorai.pfa.module1.entities.camion.Assurance;
import com.izorai.pfa.module1.mappers.camion.AssuranceMapper;
import com.izorai.pfa.module1.repository.camion.AssuranceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AssuranceServiceImplTest {

    @Mock
    private AssuranceRepository assuranceRepository;

    @Mock
    private AssuranceMapper assuranceMapper;

    @InjectMocks
    private AssuranceServiceImpl assuranceService;

    private AssuranceDTO assuranceDTO;
    private Assurance assurance;

    @BeforeEach
    void setUp() {
        // Crée des objets de test
        assuranceDTO = new AssuranceDTO(
                1L, "AXA", "Tous risques", 50000,
                LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1),
                3000, 11223344L
        );

        assurance = new Assurance();
        assurance.setNumeroContrat(1L);
        assurance.setCompany("AXA");
        assurance.setTypeCouverture("Tous risques");
        assurance.setMontant(50000);
        assurance.setDateDebut(LocalDate.of(2024, 1, 1));
        assurance.setDateExpiration(LocalDate.of(2025, 1, 1));
        assurance.setPrimeAnnuelle(3000);
        assurance.setNumCarteVerte(11223344L);
    }

    @Test
    void testAddNewAssurance() {
        // Simulation des comportements
        when(assuranceMapper.fromAssuranceDto(assuranceDTO)).thenReturn(assurance);
        when(assuranceRepository.save(assurance)).thenReturn(assurance);
        when(assuranceMapper.toAssuranceDto(assurance)).thenReturn(assuranceDTO);

        // Exécution du test
        AssuranceDTO result = assuranceService.addNewAssurance(assuranceDTO);

        // Vérification des résultats
        assertNotNull(result, "Le résultat retourné par le service addNewAssurance est nul");
        assertEquals(assuranceDTO.numeroContrat(), result.numeroContrat(),
                "Les numéros de contrat ne correspondent pas");
        verify(assuranceMapper).fromAssuranceDto(assuranceDTO);
        verify(assuranceRepository).save(assurance);
        verify(assuranceMapper).toAssuranceDto(assurance);
    }

    @Test
    void testGetAllAssurances() {
        // Simulation des comportements
        when(assuranceRepository.findAll()).thenReturn(List.of(assurance));
        when(assuranceMapper.toAssuranceDto(assurance)).thenReturn(assuranceDTO);

        // Exécution du test
        List<AssuranceDTO> result = assuranceService.getAllAssurances();

        // Vérification des résultats
        assertNotNull(result, "La liste des assurances retournée est nulle");
        assertFalse(result.isEmpty(), "La liste des assurances est vide");
        assertEquals(1, result.size(), "La taille de la liste des assurances est incorrecte");
        assertEquals(assuranceDTO.numeroContrat(), result.get(0).numeroContrat(),
                "Le numéro de contrat de l'assurance ne correspond pas");
    }

    @Test
    void testGetAssuranceById() {
        // Mock du repository pour retourner un Optional contenant l'Assurance
        when(assuranceRepository.findById(1L)).thenReturn(Optional.of(assurance));
        // Mock du mapper pour retourner l'AssuranceDTO
        when(assuranceMapper.toAssuranceDto(assurance)).thenReturn(assuranceDTO);

        // Appeler la méthode à tester
        AssuranceDTO result = assuranceService.getAssuranceById(1L);

        // Vérifier le résultat
        assertNotNull(result, "Le résultat ne doit pas être nul");
        assertEquals(assuranceDTO.numeroContrat(), result.numeroContrat(), "Le numéro de contrat ne correspond pas");
        assertEquals(assuranceDTO.company(), result.company(), "La compagnie ne correspond pas");

    }

    @Test
    void testUpdateAssurance() {
        // Simulation des comportements
        when(assuranceRepository.findById(1L)).thenReturn(Optional.of(assurance));
        when(assuranceMapper.toAssuranceDto(assurance)).thenReturn(assuranceDTO);

        AssuranceDTO assuranceDetails = new AssuranceDTO(
                1L, "Allianz", "Tous risques", 60000,
                LocalDate.of(2024, 2, 1), LocalDate.of(2025, 2, 1),
                3500, 55667788L
        );

        // Exécution du test
        AssuranceDTO result = assuranceService.updateAssurance(1L, assuranceDetails);

        // Vérification des résultats
        assertNotNull(result, "Le résultat de la mise à jour est nul");
        assertEquals(assuranceDetails.numeroContrat(), result.numeroContrat(),
                "Les numéros de contrat ne correspondent pas");
        assertEquals(assuranceDetails.company(), result.company(),
                "Les compagnies d'assurance ne correspondent pas");
        verify(assuranceRepository).findById(1L);
        verify(assuranceRepository).save(assurance);
    }

    @Test
    void testDeleteAssurance() {
        // Simulation des comportements
        doNothing().when(assuranceRepository).deleteByNumeroContrat(1L);

        // Exécution du test
        assuranceService.deleteAssurance(1L);

        // Vérification des interactions
        verify(assuranceRepository).deleteByNumeroContrat(1L);
    }
}