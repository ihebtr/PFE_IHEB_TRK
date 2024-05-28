package tn.com.sigrh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.com.sigrh.models.Classe;


@Repository
public interface ClasseRepo extends JpaRepository <Classe,Long> {
}
