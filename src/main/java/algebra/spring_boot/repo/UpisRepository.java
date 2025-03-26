package algebra.spring_boot.repo;

import algebra.spring_boot.model.Upis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpisRepository extends JpaRepository<Upis, Long> {
}