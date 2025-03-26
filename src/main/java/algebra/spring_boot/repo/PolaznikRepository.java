package algebra.spring_boot.repo;

import algebra.spring_boot.model.Polaznik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolaznikRepository extends JpaRepository<Polaznik, Long> {
}