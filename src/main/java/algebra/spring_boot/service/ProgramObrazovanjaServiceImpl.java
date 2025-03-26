package algebra.spring_boot.service;

import algebra.spring_boot.dto.CreateProgramObrazovanjaDto;
import algebra.spring_boot.model.ProgramObrazovanja;
import algebra.spring_boot.repo.ProgramObrazovanjaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramObrazovanjaServiceImpl implements ProgramObrazovanjaService {

    private final ProgramObrazovanjaRepository programRepository;

    public ProgramObrazovanjaServiceImpl(ProgramObrazovanjaRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public List<ProgramObrazovanja> findAll() {
        return programRepository.findAll();
    }

    @Override
    public Optional<ProgramObrazovanja> findById(Long id) {
        return programRepository.findById(id);
    }

    @Override
    public ProgramObrazovanja create(CreateProgramObrazovanjaDto dto) {
        ProgramObrazovanja program = new ProgramObrazovanja();
        program.setNaziv(dto.getNaziv());
        program.setCsvET(dto.getCsvET());
        return programRepository.save(program);
    }

    @Override
    public void delete(Long id) {
        programRepository.deleteById(id);
    }
}