package tn.com.sigrh.service;

import tn.com.sigrh.models.Bulletin;
import tn.com.sigrh.models.Classe;

import java.util.List;

public interface BulletinServiceI {
    Bulletin ajouterbulletin(Bulletin bulletin);

    List<Bulletin> afficherbulletin();

    Bulletin afficherbulletin(Long id);

    void supprimerbulletin(Long id);

    Bulletin modifierbulletin(Bulletin bulletin);
}
