package tn.com.sigrh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.com.sigrh.models.Matiere;
import tn.com.sigrh.models.Note;

import java.util.List;

@Repository
public interface MatiereRepo extends JpaRepository<Matiere,Long> {

}
