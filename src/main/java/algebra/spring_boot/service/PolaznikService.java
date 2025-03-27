package algebra.spring_boot.service;

import algebra.spring_boot.dto.CreatePolaznikDto;
import algebra.spring_boot.dto.UpdatePolaznikDto;
import algebra.spring_boot.model.Polaznik;
import java.util.List;
import java.util.Optional;

public interface PolaznikService {
    List<Polaznik> fetchAll();
    Optional<Polaznik> findById(Long id);
    Polaznik create(CreatePolaznikDto dto);

    Polaznik update(Long id, UpdatePolaznikDto dto);

    void delete(Long id);
}