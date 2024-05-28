package tn.com.sigrh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.sigrh.models.Classe;

import tn.com.sigrh.models.Niveau;
import tn.com.sigrh.repo.ClasseRepo;
import tn.com.sigrh.repo.MatiereRepo;
import tn.com.sigrh.repo.NiveauRepo;

import java.util.ArrayList;
import java.util.List;
@Service
public class ClasseService implements ClasseServiceI{
    @Autowired
    ClasseRepo classerepo;
    @Autowired
    private NiveauRepo niveaurepo;
    @Autowired
    MatiereRepo matiererepo;
    @Override
    public Classe ajouterclasse(Classe classe) {
        classerepo.save(classe);
        return classe;
    }

    @Override
    public List<Classe> afficherclasses() {
        List<Classe> Classes = new ArrayList<>();
        classerepo.findAll().forEach(e -> Classes.add(e));
        return Classes;
    }

    @Override
    public Classe afficherclasse(Long id) {
        Classe classe = classerepo.findById(id).get();
        return classe;
    }

    @Override
    public void supprimerclasse(Long id) {
        Classe classe = classerepo.findById(id).get();
        classerepo.delete(classe);
    }

    @Override
    public Classe modifierclasse(Classe classe) {
        classerepo.save(classe);
        return classe;
    }
    public void affecterClasseANiveau(Long classeId, Long niveauId) {
        Classe classe = classerepo.findById(classeId).orElseThrow(() -> new RuntimeException("Classe not found"));
        Niveau niveau = niveaurepo.findById(niveauId).orElseThrow(() -> new RuntimeException("Niveau not found"));

        classe.setNiveau(niveau);
        classerepo.save(classe);
    }

}
