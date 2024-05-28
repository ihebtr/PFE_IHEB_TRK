package tn.com.sigrh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.com.sigrh.models.Coefficient;
@Repository
public interface CoefficientRepo extends JpaRepository<Coefficient, Long> {
}
