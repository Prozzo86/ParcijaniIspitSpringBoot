package algebra.spring_boot.service;

import algebra.spring_boot.dto.CreateProgramObrazovanjaDto;
import algebra.spring_boot.dto.UpdateProgramObrazovanjaDto;
import algebra.spring_boot.model.ProgramObrazovanja;
import algebra.spring_boot.repo.ProgramObrazovanjaRepository;
import algebra.spring_boot.repo.UpisRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramObrazovanjaServiceImpl implements ProgramObrazovanjaService {

    private final ProgramObrazovanjaRepository programRepository;
    private final UpisRepository upisRepository;

    public ProgramObrazovanjaServiceImpl(ProgramObrazovanjaRepository programRepository,
                                         UpisRepository upisRepository) {
        this.programRepository = programRepository;
        this.upisRepository = upisRepository;
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
    public ProgramObrazovanja update(Long id, UpdateProgramObrazovanjaDto dto) {
        ProgramObrazovanja program = programRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program obrazovanja s ID-em " + id + " nije pronađen"));
        program.setNaziv(dto.getNaziv());
        program.setCsvET(dto.getCsvET());
        return programRepository.save(program);
    }

    @Override
    public void delete(Long id) {
        if (!programRepository.existsById(id)) {
            throw new RuntimeException("Program obrazovanja s ID-em " + id + " ne postoji");
        }

        upisRepository.deleteByProgramObrazovanjaId(id);

        programRepository.deleteById(id);
    }
}
