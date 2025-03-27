package algebra.spring_boot.repo;

import algebra.spring_boot.model.Polaznik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// PolaznikRepository.java
@Repository
public interface PolaznikRepository extends JpaRepository<Polaznik, Long> {
    @Query("SELECT p FROM Polaznik p WHERE p.ime LIKE %:ime%")
    List<Polaznik> findByImeContaining(String ime);

    @Query("SELECT p FROM Polaznik p WHERE p.prezime LIKE %:prezime%")
    List<Polaznik> findByPrezimeContaining(String prezime);
}