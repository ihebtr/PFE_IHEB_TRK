package tn.com.sigrh.service;

import tn.com.sigrh.models.Niveau;

import java.util.List;

public interface NiveauServiceI {
    Niveau ajouterNiveau(Niveau niveau);

    List<Niveau> afficherniveaux();

    Niveau afficherniveau(Long id);

    void supprimerniveau(Long id);

    Niveau modifierniveau(Niveau n);
}
