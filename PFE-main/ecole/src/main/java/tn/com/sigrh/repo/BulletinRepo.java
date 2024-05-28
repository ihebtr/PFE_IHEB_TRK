package tn.com.sigrh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.com.sigrh.models.Bulletin;
@Repository
public interface BulletinRepo extends JpaRepository<Bulletin, Long> {
}
