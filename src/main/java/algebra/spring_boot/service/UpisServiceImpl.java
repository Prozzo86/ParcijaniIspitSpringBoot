package algebra.spring_boot.service;


import algebra.spring_boot.dto.CreateUpisDto;
import algebra.spring_boot.model.Polaznik;
import algebra.spring_boot.model.ProgramObrazovanja;
import algebra.spring_boot.model.Upis;
import algebra.spring_boot.repo.PolaznikRepository;
import algebra.spring_boot.repo.ProgramObrazovanjaRepository;
import algebra.spring_boot.repo.UpisRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UpisServiceImpl implements UpisService {

    private final UpisRepository upisRepository;
    private final PolaznikRepository polaznikRepository;
    private final ProgramObrazovanjaRepository programRepository;

    public UpisServiceImpl(UpisRepository upisRepository,
                           PolaznikRepository polaznikRepository,
                           ProgramObrazovanjaRepository programRepository) {
        this.upisRepository = upisRepository;
        this.polaznikRepository = polaznikRepository;
        this.programRepository = programRepository;
    }

    @Override
    public List<Upis> findAll() {
        return upisRepository.findAll();
    }

    @Override
    public Optional<Upis> findById(Long id) {
        return upisRepository.findById(id);
    }

    @Override
    public Upis create(CreateUpisDto dto) {
        Optional<Polaznik> polaznik = polaznikRepository.findById(dto.getPolaznikID());
        Optional<ProgramObrazovanja> program = programRepository.findById(dto.getProgramObrazovanjaID());

        if (polaznik.isEmpty() || program.isEmpty()) {
            throw new IllegalArgumentException("Polaznik ili program nije pronađen");
        }

        Upis upis = new Upis();
        upis.setPolaznik(polaznik.get());
        upis.setProgramObrazovanja(program.get());

        return upisRepository.save(upis);
    }

    @Override
    public void delete(Long id) {
        upisRepository.deleteById(id);
    }
}