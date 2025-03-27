package algebra.spring_boot.service;

import algebra.spring_boot.dto.CreateProgramObrazovanjaDto;
import algebra.spring_boot.dto.UpdateProgramObrazovanjaDto;
import algebra.spring_boot.model.ProgramObrazovanja;
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
class ProgramObrazovanjaServiceImplTest {

    @Mock
    private ProgramObrazovanjaRepository programRepository;

    @Mock
    private UpisRepository upisRepository;

    @InjectMocks
    private ProgramObrazovanjaServiceImpl programService;

    private ProgramObrazovanja program;

    @BeforeEach
    void setUp() {
        program = new ProgramObrazovanja();
        program.setId(1L);
        program.setNaziv("Programiranje");
        program.setCsvET("180");
    }

    @Test
    void findAll_ShouldReturnAllPrograms() {

        when(programRepository.findAll()).thenReturn(List.of(program));


        List<ProgramObrazovanja> result = programService.findAll();


        assertEquals(1, result.size());
        assertEquals(program, result.get(0));
        verify(programRepository, times(1)).findAll();
    }

    @Test
    void findById_WhenProgramExists_ShouldReturnProgram() {

        when(programRepository.findById(1L)).thenReturn(Optional.of(program));

        Optional<ProgramObrazovanja> result = programService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(program, result.get());
        verify(programRepository, times(1)).findById(1L);
    }

    @Test
    void findById_WhenProgramNotExists_ShouldReturnEmpty() {
        when(programRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ProgramObrazovanja> result = programService.findById(1L);

        assertTrue(result.isEmpty());
        verify(programRepository, times(1)).findById(1L);
    }
/*
    @Test
    void create_ShouldSaveNewProgram() {
        CreateProgramObrazovanjaDto dto = new CreateProgramObrazovanjaDto();
        dto.setNaziv("Programiranje");
        dto.setCsvET("180");

        when(programRepository.save(any(ProgramObrazovanja.class))).thenReturn(program);

        ProgramObrazovanja result = programService.create(dto);

        assertNotNull(result);
        assertEquals("Programiranje", result.getNaziv());
        assertEquals(180L, result.getId());
        verify(programRepository, times(1)).save(any(ProgramObrazovanja.class));
    }*/
/*
    @Test
    void update_ShouldUpdateExistingProgram() {
        UpdateProgramObrazovanjaDto dto = new UpdateProgramObrazovanjaDto();
        dto.setNaziv("Novi naziv");
        dto.setCsvET("250");

        when(programRepository.findById(1L)).thenReturn(Optional.of(program));
        when(programRepository.save(any(ProgramObrazovanja.class))).thenReturn(program);

        ProgramObrazovanja result = programService.update(250L, dto);

        assertNotNull(result);
        assertEquals("Novi naziv", result.getNaziv());
        assertEquals(250, result.getCsvET());
        verify(programRepository, times(1)).findById(1L);
        verify(programRepository, times(1)).save(any(ProgramObrazovanja.class));
    }
*/
    @Test
    void update_WhenProgramNotExists_ShouldThrowException() {
        UpdateProgramObrazovanjaDto dto = new UpdateProgramObrazovanjaDto();
        when(programRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> programService.update(1L, dto));
        verify(programRepository, never()).save(any(ProgramObrazovanja.class));
    }

    @Test
    void delete_ShouldDeleteProgramAndRelatedUpisi() {
        when(programRepository.existsById(1L)).thenReturn(true);
        doNothing().when(upisRepository).deleteByProgramObrazovanjaId(1L);
        doNothing().when(programRepository).deleteById(1L);

        programService.delete(1L);

        verify(upisRepository, times(1)).deleteByProgramObrazovanjaId(1L);
        verify(programRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_WhenProgramNotExists_ShouldThrowException() {
        when(programRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> programService.delete(1L));
        verify(upisRepository, never()).deleteByProgramObrazovanjaId(1L);
        verify(programRepository, never()).deleteById(1L);
    }
}