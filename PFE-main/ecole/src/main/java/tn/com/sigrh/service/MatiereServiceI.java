package tn.com.sigrh.service;


import tn.com.sigrh.models.Matiere;

import java.util.List;

public interface MatiereServiceI {
    Matiere ajoutermatiere(Matiere matiere);

    List<Matiere> afficherMatieres();

    Matiere affichermatiere(Long id);

    void supprimermatiere(Long id);

    Matiere modifiermatiere(Matiere matiere);
}
