package tn.com.sigrh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.com.sigrh.models.Moyenne;
@Repository
public interface MoyenneRepo extends JpaRepository<Moyenne, Long> {

}
