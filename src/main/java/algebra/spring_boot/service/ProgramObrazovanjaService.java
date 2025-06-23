package algebra.spring_boot.service;

import algebra.spring_boot.dto.CreateProgramObrazovanjaDto;
import algebra.spring_boot.dto.UpdateProgramObrazovanjaDto;
import algebra.spring_boot.model.ProgramObrazovanja;
import java.util.List;
import java.util.Optional;

public interface ProgramObrazovanjaService {
    List<ProgramObrazovanja> findAll();
    Optional<ProgramObrazovanja> findById(Long id);
    ProgramObrazovanja create(CreateProgramObrazovanjaDto dto);

    ProgramObrazovanja update(Long id, UpdateProgramObrazovanjaDto dto);
    void delete(Long id);
}