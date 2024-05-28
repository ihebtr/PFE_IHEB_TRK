package tn.com.sigrh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.com.sigrh.models.Niveau;

@Repository
public interface NiveauRepo extends JpaRepository <Niveau,Long> {
}
