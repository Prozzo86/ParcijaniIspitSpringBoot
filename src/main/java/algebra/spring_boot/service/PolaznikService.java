package algebra.spring_boot.service;

import algebra.spring_boot.dto.CreatePolaznikDto;
import algebra.spring_boot.model.Polaznik;
import java.util.List;
import java.util.Optional;

public interface PolaznikService {
    List<Polaznik> findAll();
    Optional<Polaznik> findById(Long id);
    Polaznik create(CreatePolaznikDto dto);
    void delete(Long id);
}