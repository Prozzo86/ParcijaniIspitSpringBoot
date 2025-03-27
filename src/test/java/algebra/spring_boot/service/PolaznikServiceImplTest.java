package algebra.spring_boot.service;

import algebra.spring_boot.dto.CreatePolaznikDto;
import algebra.spring_boot.dto.UpdatePolaznikDto;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PolaznikServiceImplTest {

    @Mock
    private PolaznikRepository polaznikRepository;

    @Mock
    private ProgramObrazovanjaRepository programObrazovanjaRepository;

    @Mock
    private UpisRepository upisRepository;

    @InjectMocks
    private PolaznikServiceImpl polaznikService;

    private Polaznik polaznik;
    private ProgramObrazovanja program;
    private Upis upis;

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
    void fetchAll_ShouldReturnAllPolaznici() {

        List<Polaznik> expectedPolaznici = Arrays.asList(polaznik);
        when(polaznikRepository.findAll()).thenReturn(expectedPolaznici);


        List<Polaznik> result = polaznikService.fetchAll();


        assertEquals(expectedPolaznici, result);
        verify(polaznikRepository, times(1)).findAll();
    }

    @Test
    void findById_WhenPolaznikExists_ShouldReturnPolaznik() {
        when(polaznikRepository.findById(1L)).thenReturn(Optional.of(polaznik));

        Optional<Polaznik> result = polaznikService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(polaznik, result.get());
        verify(polaznikRepository, times(1)).findById(1L);
    }

    @Test
    void findById_WhenPolaznikNotExists_ShouldReturnEmpty() {
        when(polaznikRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Polaznik> result = polaznikService.findById(1L);

        assertFalse(result.isPresent());
        verify(polaznikRepository, times(1)).findById(1L);
    }

    @Test
    void create_WithoutProgram_ShouldCreateOnlyPolaznik() {
        CreatePolaznikDto dto = new CreatePolaznikDto();
        dto.setIme("Ivan");
        dto.setPrezime("Horvat");
        dto.setProgramId(null);

        when(polaznikRepository.save(any(Polaznik.class))).thenReturn(polaznik);

        Polaznik result = polaznikService.create(dto);

        assertNotNull(result);
        assertEquals("Ivan", result.getIme());
        assertEquals("Horvat", result.getPrezime());
        verify(polaznikRepository, times(1)).save(any(Polaznik.class));
        verify(upisRepository, never()).save(any(Upis.class));
    }

    @Test
    void create_WithProgram_ShouldCreatePolaznikAndUpis() {
        CreatePolaznikDto dto = new CreatePolaznikDto();
        dto.setIme("Ivan");
        dto.setPrezime("Horvat");
        dto.setProgramId(1L);

        when(polaznikRepository.save(any(Polaznik.class))).thenReturn(polaznik);
        when(programObrazovanjaRepository.findById(1L)).thenReturn(Optional.of(program));
        when(upisRepository.save(any(Upis.class))).thenReturn(upis);

        Polaznik result = polaznikService.create(dto);

        assertNotNull(result);
        verify(polaznikRepository, times(1)).save(any(Polaznik.class));
        verify(programObrazovanjaRepository, times(1)).findById(1L);
        verify(upisRepository, times(1)).save(any(Upis.class));
    }

    @Test
    void create_WithInvalidProgram_ShouldThrowException() {
        CreatePolaznikDto dto = new CreatePolaznikDto();
        dto.setIme("Ivan");
        dto.setPrezime("Horvat");
        dto.setProgramId(99L);

        when(polaznikRepository.save(any(Polaznik.class))).thenReturn(polaznik);
        when(programObrazovanjaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> polaznikService.create(dto));
        verify(polaznikRepository, times(1)).save(any(Polaznik.class));
        verify(programObrazovanjaRepository, times(1)).findById(99L);
        verify(upisRepository, never()).save(any(Upis.class));
    }

    @Test
    void update_ShouldUpdatePolaznikAndUpis() {

        UpdatePolaznikDto dto = new UpdatePolaznikDto();
        dto.setIme("Novo ime");
        dto.setPrezime("Novo prezime");
        dto.setProgramId(1L);

        when(polaznikRepository.findById(1L)).thenReturn(Optional.of(polaznik));
        when(programObrazovanjaRepository.findById(1L)).thenReturn(Optional.of(program));
       // when(upisRepository.findByPolaznikPolaznikID(1L)).thenReturn(Optional.of(upis));
        when(polaznikRepository.save(any(Polaznik.class))).thenReturn(polaznik);

        Polaznik result = polaznikService.update(1L, dto);

        assertNotNull(result);
        assertEquals("Novo ime", result.getIme());
        assertEquals("Novo prezime", result.getPrezime());
        verify(polaznikRepository, times(1)).findById(1L);
        verify(programObrazovanjaRepository, times(1)).findById(1L);
        verify(upisRepository, times(1)).findByPolaznikPolaznikID(1L);
        verify(upisRepository, times(1)).save(any(Upis.class));
        verify(polaznikRepository, times(1)).save(any(Polaznik.class));
    }

    @Test
    void update_WhenPolaznikNotFound_ShouldThrowException() {
        UpdatePolaznikDto dto = new UpdatePolaznikDto();
        when(polaznikRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> polaznikService.update(1L, dto));
        verify(polaznikRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(polaznikRepository, programObrazovanjaRepository, upisRepository);
    }

    @Test
    void update_WithNewProgram_ShouldCreateNewUpis() {
        UpdatePolaznikDto dto = new UpdatePolaznikDto();
        dto.setIme("Ivan");
        dto.setPrezime("Horvat");
        dto.setProgramId(2L);

        ProgramObrazovanja noviProgram = new ProgramObrazovanja();
        noviProgram.setId(2L);

        when(polaznikRepository.findById(1L)).thenReturn(Optional.of(polaznik));
        when(programObrazovanjaRepository.findById(2L)).thenReturn(Optional.of(noviProgram));
       // when(upisRepository.findByPolaznikPolaznikID(1L)).thenReturn(Optional.empty());
        when(polaznikRepository.save(any(Polaznik.class))).thenReturn(polaznik);

        Polaznik result = polaznikService.update(1L, dto);

        assertNotNull(result);
        verify(upisRepository, times(1)).save(any(Upis.class));
    }

    @Test
    void delete_ShouldDeleteUpisAndPolaznik() {
        doNothing().when(upisRepository).deleteByPolaznikId(1L);
        doNothing().when(polaznikRepository).deleteById(1L);

        polaznikService.delete(1L);

        verify(upisRepository, times(1)).deleteByPolaznikId(1L);
        verify(polaznikRepository, times(1)).deleteById(1L);
    }
}