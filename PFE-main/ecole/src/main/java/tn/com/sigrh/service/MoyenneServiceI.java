package tn.com.sigrh.service;

import tn.com.sigrh.models.Matiere;
import tn.com.sigrh.models.Moyenne;

import java.util.List;

public interface MoyenneServiceI {
    Moyenne ajoutermoyenne(Moyenne moyenne);

    List<Moyenne> afficherMoyennes();

    Moyenne affichermoyenne(Long id);

    void supprimermoyenne(Long id);

    Moyenne modifiermoyenne(Moyenne moyenne);

    double calculerMoyenneMatiere(Matiere matiere);
}
