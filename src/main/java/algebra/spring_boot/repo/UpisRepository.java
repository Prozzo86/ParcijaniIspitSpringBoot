package algebra.spring_boot.repo;

import algebra.spring_boot.model.Upis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UpisRepository extends JpaRepository<Upis, Long> {
        List<Upis> findByPolaznikPolaznikID(Long polaznikId);
        List<Upis> findByProgramObrazovanjaId(Long programId);

        @Modifying
        @Query("DELETE FROM Upis u WHERE u.polaznik.id = :polaznikId")
        void deleteByPolaznikId(@Param("polaznikId") Long polaznikId);

        @Modifying
        @Query("DELETE FROM Upis u WHERE u.programObrazovanja.id = :programId")
        void deleteByProgramObrazovanjaId(@Param("programId") Long programId);
}