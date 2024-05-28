package tn.com.sigrh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.sigrh.models.Niveau;
import tn.com.sigrh.repo.NiveauRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class NiveauService implements NiveauServiceI{

    @Autowired
    NiveauRepo niveauRepo;
    @Override
    public Niveau ajouterNiveau(Niveau niveau) {
       niveauRepo.save(niveau);
        return niveau;
    }

    @Override
    public List<Niveau> afficherniveaux() {
        List<Niveau> Niveaux = new ArrayList<>();
        niveauRepo.findAll().forEach(e -> Niveaux.add(e));
        return Niveaux;
    }

    @Override
    public Niveau afficherniveau(Long id) {
        Niveau n = niveauRepo.findById(id).get();
        return n;
    }

    @Override
    public void supprimerniveau(Long id) {
        Niveau n = niveauRepo.findById(id).get();
        niveauRepo.delete(n);
    }

    @Override
    public Niveau modifierniveau(Niveau n) {
        niveauRepo.save(n);
        return n;
    }
}
