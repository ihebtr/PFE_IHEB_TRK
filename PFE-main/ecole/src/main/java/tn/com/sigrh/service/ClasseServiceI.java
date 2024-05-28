package tn.com.sigrh.service;

import tn.com.sigrh.models.Classe;

import java.util.List;

public interface ClasseServiceI {
    Classe ajouterclasse(Classe classe);

    List<Classe> afficherclasses();

    Classe afficherclasse(Long id);

    void supprimerclasse(Long id);

    Classe modifierclasse(Classe classe);

    void affecterClasseANiveau(Long classeId, Long niveauId);
}
