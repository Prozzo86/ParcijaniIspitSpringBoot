package algebra.spring_boot.service;

import algebra.spring_boot.dto.CreatePolaznikDto;
import algebra.spring_boot.dto.UpdatePolaznikDto;
import algebra.spring_boot.model.Polaznik;
import algebra.spring_boot.model.ProgramObrazovanja;
import algebra.spring_boot.model.Upis;
import algebra.spring_boot.repo.UpisRepository;
import algebra.spring_boot.repo.PolaznikRepository;
import algebra.spring_boot.repo.ProgramObrazovanjaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PolaznikServiceImpl implements PolaznikService {
    private final PolaznikRepository polaznikRepository;
    private final ProgramObrazovanjaRepository programObrazovanjaRepository;
    private final UpisRepository upisRepository;

    public PolaznikServiceImpl(
            PolaznikRepository polaznikRepository,
            ProgramObrazovanjaRepository programObrazovanjaRepository,
            UpisRepository upisRepository) {
        this.polaznikRepository = polaznikRepository;
        this.programObrazovanjaRepository = programObrazovanjaRepository;
        this.upisRepository = upisRepository;
    }

    @Override
    public List<Polaznik> fetchAll() {
        return polaznikRepository.findAll();
    }

    @Override
    public Optional<Polaznik> findById(Long id) {
        return polaznikRepository.findById(id);
    }

    @Override
    public Polaznik create(CreatePolaznikDto dto) {
        // Kreiranje polaznika
        Polaznik polaznik = new Polaznik();
        polaznik.setIme(dto.getIme());
        polaznik.setPrezime(dto.getPrezime());

        // Spremanje polaznika
        Polaznik savedPolaznik = polaznikRepository.save(polaznik);

        // Ako je naveden program, dodaj upis
        if (dto.getProgramId() != null) {
            ProgramObrazovanja program = programObrazovanjaRepository.findById(dto.getProgramId())
                    .orElseThrow(() -> new RuntimeException("Program nije pronađen"));

            Upis upis = new Upis();
            upis.setPolaznik(savedPolaznik);
            upis.setProgramObrazovanja(program);
            upisRepository.save(upis);
        }

        return savedPolaznik;
    }

    @Override
    public Polaznik update(Long id, UpdatePolaznikDto dto) {
        Polaznik polaznik = polaznikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Polaznik nije pronađen"));

        polaznik.setIme(dto.getIme());
        polaznik.setPrezime(dto.getPrezime());

        // Ažuriranje programa ako je potrebno
        if (dto.getProgramId() != null) {
            ProgramObrazovanja program = programObrazovanjaRepository.findById(dto.getProgramId())
                    .orElseThrow(() -> new RuntimeException("Program nije pronađen"));

            List<Upis> existingUpisi = upisRepository.findByPolaznikPolaznikID(id);

            if (!existingUpisi.isEmpty()) {
                // Ažuriraj prvi upis (ili možete implementirati drugu logiku)
                Upis upis = existingUpisi.get(0);
                upis.setProgramObrazovanja(program);
                upisRepository.save(upis);
            } else {
                Upis newUpis = new Upis();
                newUpis.setPolaznik(polaznik);
                newUpis.setProgramObrazovanja(program);
                upisRepository.save(newUpis);
            }
        }

        return polaznikRepository.save(polaznik);
    }

    @Override
    public void delete(Long id) {

        upisRepository.deleteByPolaznikId(id);
        polaznikRepository.deleteById(id);
    }
}
