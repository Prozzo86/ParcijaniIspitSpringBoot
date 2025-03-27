package algebra.spring_boot.service;

import algebra.spring_boot.dto.CreateUpisDto;
import algebra.spring_boot.dto.UpdateProgramObrazovanjaDto;
import algebra.spring_boot.dto.UpdateUpisDto;
import algebra.spring_boot.model.ProgramObrazovanja;
import algebra.spring_boot.model.Upis;
import java.util.List;
import java.util.Optional;

public interface UpisService {
    List<Upis> findAll();
    Optional<Upis> findById(Long id);
    Upis create(CreateUpisDto dto);
    void delete(Long id);
    Upis update(Long id, UpdateUpisDto dto);

}