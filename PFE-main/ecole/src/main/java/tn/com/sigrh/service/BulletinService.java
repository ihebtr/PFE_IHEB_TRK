package tn.com.sigrh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.sigrh.models.Bulletin;
import tn.com.sigrh.repo.BulletinRepo;
import java.util.ArrayList;
import java.util.List;
@Service
public class BulletinService implements BulletinServiceI{
    @Autowired
    BulletinRepo bulletinrepo;
    @Override
    public Bulletin ajouterbulletin(Bulletin bulletin) {
        bulletinrepo.save(bulletin);
        return bulletin;
    }

    @Override
    public List<Bulletin> afficherbulletin() {
        List<Bulletin> Bulletins = new ArrayList<>();
        bulletinrepo.findAll().forEach(e -> Bulletins.add(e));
        return Bulletins;
    }

    @Override
    public Bulletin afficherbulletin(Long id) {
        Bulletin bulletin = bulletinrepo.findById(id).get();
        return bulletin;
    }

    @Override
    public void supprimerbulletin(Long id) {
        Bulletin bulletin = bulletinrepo.findById(id).get();
        bulletinrepo.delete(bulletin);
    }

    @Override
    public Bulletin modifierbulletin(Bulletin bulletin) {
        bulletinrepo.save(bulletin);
        return bulletin;
    }
}
