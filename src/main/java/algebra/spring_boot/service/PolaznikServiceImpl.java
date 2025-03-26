package algebra.spring_boot.service;


import algebra.spring_boot.dto.CreatePolaznikDto;
import algebra.spring_boot.model.Polaznik;
import algebra.spring_boot.repo.PolaznikRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PolaznikServiceImpl implements PolaznikService {

    private final PolaznikRepository polaznikRepository;

    public PolaznikServiceImpl(PolaznikRepository polaznikRepository) {
        this.polaznikRepository = polaznikRepository;
    }

    @Override
    public List<Polaznik> findAll() {
        return polaznikRepository.findAll();
    }

    @Override
    public Optional<Polaznik> findById(Long id) {
        return polaznikRepository.findById(id);
    }

    @Override
    public Polaznik create(CreatePolaznikDto dto) {
        Polaznik polaznik = new Polaznik();
        polaznik.setIme(dto.getIme());
        polaznik.setPrezime(dto.getPrezime());
        return polaznikRepository.save(polaznik);
    }

    @Override
    public void delete(Long id) {
        polaznikRepository.deleteById(id);
    }
}