package tn.com.sigrh.service;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.sigrh.models.*;
import tn.com.sigrh.repo.CoefficientRepo;
import tn.com.sigrh.repo.MatiereRepo;
import tn.com.sigrh.repo.MoyenneRepo;
import tn.com.sigrh.repo.NoteRepo;

import java.util.ArrayList;
import java.util.List;
@Service
public class MoyenneService implements MoyenneServiceI{

    @Autowired
    MoyenneRepo moyennerepo;
    @Autowired
    private CoefficientRepo coefficientRepository;
    @Autowired
    NoteRepo noterepo;
    @Autowired
    MatiereRepo matiererepo;
    @Override
    public Moyenne ajoutermoyenne(Moyenne moyenne) {
        moyennerepo.save(moyenne);
        return moyenne;
    }

    @Override
    public List<Moyenne> afficherMoyennes() {
        List<Moyenne> Moyennes = new ArrayList<>();
        moyennerepo.findAll().forEach(e -> Moyennes.add(e));
        return Moyennes;
    }

    @Override
    public Moyenne affichermoyenne(Long id) {
        Moyenne m = moyennerepo.findById(id).get();
        return m;
    }

    @Override
    public void supprimermoyenne(Long id) {
        Moyenne m = moyennerepo.findById(id).get();
        moyennerepo.delete(m);
    }

    @Override
    public Moyenne modifiermoyenne(Moyenne moyenne) {
        moyennerepo.save(moyenne);
        return moyenne;    }
    public double calculerMoyenneMatiere(Matiere matiere) {
        List<Note> notes = matiere.getNotes();
        if (notes == null || notes.isEmpty()) {
            return 0; // Gérer le cas où aucune note n'est disponible
        }

        double somme = 0;
        int nbNotesTotales = 0;
        for (Note note : notes) {
            double moyenneNote = note.getCc() * 0.2 + note.getTp() * 0.3 + note.getExamen() * 0.5;
            somme += moyenneNote;
            nbNotesTotales++;
        }

        // Calculer la moyenne totale
        double moyenneTotale = somme / nbNotesTotales;

        // Mettre à jour la moyenne de la matière
        matiere.setMoyenne(moyenneTotale);

        // Enregistrer la matière mise à jour dans la base de données
        matiererepo.save(matiere);

        return moyenneTotale;
    }

}

