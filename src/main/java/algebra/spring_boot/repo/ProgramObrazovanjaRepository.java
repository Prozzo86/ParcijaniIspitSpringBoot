package algebra.spring_boot.repo;

import algebra.spring_boot.model.ProgramObrazovanja;
import algebra.spring_boot.model.Upis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgramObrazovanjaRepository extends JpaRepository<ProgramObrazovanja, Long> {
    Optional<ProgramObrazovanja> findByNaziv(String naziv);
    Optional<ProgramObrazovanja> findByCsvET(String csvET);


}