package tn.com.sigrh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.sigrh.models.Classe;
import tn.com.sigrh.models.Coefficient;
import tn.com.sigrh.models.Matiere;
import tn.com.sigrh.repo.CoefficientRepo;
import tn.com.sigrh.repo.MatiereRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatiereService implements MatiereServiceI{
    @Autowired
    MatiereRepo matiererepo;
    @Autowired
    CoefficientRepo coefficientRepo;
    @Override
    public Matiere ajoutermatiere(Matiere matiere) {
        matiererepo.save(matiere);
        return matiere;
    }

    @Override
    public List<Matiere> afficherMatieres() {
        List<Matiere> Matieres = new ArrayList<>();
        matiererepo.findAll().forEach(e -> Matieres.add(e));
        return Matieres;
    }

    @Override
    public Matiere affichermatiere(Long id) {
        Matiere matiere = matiererepo.findById(id).get();
        return matiere;
    }

    @Override
    public void supprimermatiere(Long id) {
        Matiere matiere = matiererepo.findById(id).get();
        matiererepo.delete(matiere);
    }

    @Override
    public Matiere modifiermatiere(Matiere matiere) {
        matiererepo.save(matiere);
        return matiere;
    }
    public void affecterCoefficientAMatiere(Long matiereId, Long coefficientId) {
        // Récupérer la matière et le coefficient à partir de leurs identifiants
        Optional<Matiere> matiereOptional = matiererepo.findById(matiereId);
        Optional<Coefficient> coefficientOptional = coefficientRepo.findById(coefficientId);

        if (matiereOptional.isPresent() && coefficientOptional.isPresent()) {
            // Associer le coefficient à la matière
            Matiere matiere = matiereOptional.get();
            Coefficient coefficient = coefficientOptional.get();
            matiere.setCoefficient(coefficient);
            matiererepo.save(matiere);
        } else {
            // Gérer le cas où la matière ou le coefficient n'existe pas
            // (vous pouvez lever une exception, renvoyer un message d'erreur, etc.)
        }
    }
    public double calculerMoyenneGenerale() {
        // Récupération de toutes les matières de la base de données
        List<Matiere> matieres = matiererepo.findAll();

        // Initialisation des variables pour la somme des moyennes pondérées et la somme des coefficients
        double sommeMoyennesPonderees = 0;
        double sommeCoefficients = 0;

        // Calcul de la somme des moyennes pondérées et de la somme des coefficients
        for (Matiere matiere : matieres) {
            double moyenne = matiere.getMoyenne();
            double coefficient = matiere.getCoefficient().getValeur();
            sommeMoyennesPonderees += moyenne * coefficient;
            sommeCoefficients += coefficient;
        }

        // Calcul de la moyenne générale
        double moyenneGenerale = sommeMoyennesPonderees / sommeCoefficients;

        return moyenneGenerale;
    }
}
