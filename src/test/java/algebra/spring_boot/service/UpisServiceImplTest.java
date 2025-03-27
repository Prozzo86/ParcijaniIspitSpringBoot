package algebra.spring_boot.service;

import algebra.spring_boot.dto.CreateUpisDto;
import algebra.spring_boot.dto.UpdateUpisDto;
import algebra.spring_boot.model.Polaznik;
import algebra.spring_boot.model.ProgramObrazovanja;
import algebra.spring_boot.model.Upis;
import algebra.spring_boot.repo.PolaznikRepository;
import algebra.spring_boot.repo.ProgramObrazovanjaRepository;
import algebra.spring_boot.repo.UpisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpisServiceImplTest {

    @Mock
    private UpisRepository upisRepository;

    @Mock
    private PolaznikRepository polaznikRepository;

    @Mock
    private ProgramObrazovanjaRepository programRepository;

    @InjectMocks
    private UpisServiceImpl upisService;

    private Upis upis;
    private Polaznik polaznik;
    private ProgramObrazovanja program;

    @BeforeEach
    void setUp() {
        polaznik = new Polaznik();
        polaznik.setPolaznikID(1L);
        polaznik.setIme("Ivan");
        polaznik.setPrezime("Horvat");

        program = new ProgramObrazovanja();
        program.setId(1L);
        program.setNaziv("Programiranje");

        upis = new Upis();
        upis.setUpisID(1L);
        upis.setPolaznik(polaznik);
        upis.setProgramObrazovanja(program);
    }

    @Test
    void findAll_ShouldReturnAllUpisi() {

        when(upisRepository.findAll()).thenReturn(List.of(upis));


        List<Upis> result = upisService.findAll();


        assertEquals(1, result.size());
        verify(upisRepository, times(1)).findAll();
    }

    @Test
    void findById_WhenExists_ShouldReturnUpis() {

        when(upisRepository.findById(1L)).thenReturn(Optional.of(upis));


        Optional<Upis> result = upisService.findById(1L);


        assertTrue(result.isPresent());
        assertEquals(upis, result.get());
        verify(upisRepository, times(1)).findById(1L);
    }

    @Test
    void findById_WhenNotExists_ShouldReturnEmpty() {

        when(upisRepository.findById(1L)).thenReturn(Optional.empty());


        Optional<Upis> result = upisService.findById(1L);

        assertTrue(result.isEmpty());
        verify(upisRepository, times(1)).findById(1L);
    }

    @Test
    void create_WithValidData_ShouldCreateUpis() {
        CreateUpisDto dto = new CreateUpisDto();
        dto.setPolaznikID(1L);
        dto.setProgramObrazovanjaID(1L);

        when(polaznikRepository.findById(1L)).thenReturn(Optional.of(polaznik));
        when(programRepository.findById(1L)).thenReturn(Optional.of(program));
        when(upisRepository.save(any(Upis.class))).thenReturn(upis);

        Upis result = upisService.create(dto);

        assertNotNull(result);
        assertEquals(polaznik, result.getPolaznik());
        assertEquals(program, result.getProgramObrazovanja());
        verify(upisRepository, times(1)).save(any(Upis.class));
    }

    @Test
    void create_WithInvalidPolaznik_ShouldThrowException() {
        CreateUpisDto dto = new CreateUpisDto();
        dto.setPolaznikID(99L);
        dto.setProgramObrazovanjaID(1L);

        when(polaznikRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> upisService.create(dto));
        verify(upisRepository, never()).save(any(Upis.class));
    }

    @Test
    void create_WithInvalidProgram_ShouldThrowException() {
        CreateUpisDto dto = new CreateUpisDto();
        dto.setPolaznikID(1L);
        dto.setProgramObrazovanjaID(99L);

        when(polaznikRepository.findById(1L)).thenReturn(Optional.of(polaznik));
        when(programRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> upisService.create(dto));
        verify(upisRepository, never()).save(any(Upis.class));
    }

    @Test
    void update_ShouldUpdateUpis() {
        UpdateUpisDto dto = new UpdateUpisDto();
        dto.setPolaznikID(2L);
        dto.setProgramObrazovanjaID(2L);

        Polaznik noviPolaznik = new Polaznik();
        noviPolaznik.setPolaznikID(2L);

        ProgramObrazovanja noviProgram = new ProgramObrazovanja();
        noviProgram.setId(2L);

        when(upisRepository.findById(1L)).thenReturn(Optional.of(upis));
        when(polaznikRepository.findById(2L)).thenReturn(Optional.of(noviPolaznik));
        when(programRepository.findById(2L)).thenReturn(Optional.of(noviProgram));
        when(upisRepository.save(any(Upis.class))).thenReturn(upis);


        Upis result = upisService.update(1L, dto);


        assertNotNull(result);
        assertEquals(noviPolaznik, result.getPolaznik());
        assertEquals(noviProgram, result.getProgramObrazovanja());
        verify(upisRepository, times(1)).save(any(Upis.class));
    }

    @Test
    void update_WhenUpisNotFound_ShouldThrowException() {

        UpdateUpisDto dto = new UpdateUpisDto();
        when(upisRepository.findById(1L)).thenReturn(Optional.empty());


        assertThrows(RuntimeException.class, () -> upisService.update(1L, dto));
        verify(upisRepository, never()).save(any(Upis.class));
    }

    @Test
    void delete_ShouldDeleteUpis() {

        doNothing().when(upisRepository).deleteById(1L);


        upisService.delete(1L);


        verify(upisRepository, times(1)).deleteById(1L);
    }
}