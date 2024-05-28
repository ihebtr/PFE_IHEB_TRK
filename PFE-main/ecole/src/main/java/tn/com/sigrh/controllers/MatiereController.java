package tn.com.sigrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.com.sigrh.models.Matiere;
import tn.com.sigrh.repo.MatiereRepo;
import tn.com.sigrh.service.MatiereService;
import tn.com.sigrh.service.MoyenneService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matiere")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MatiereController {
    @Autowired
    MatiereService matiereservice;
    @Autowired
    MatiereRepo matiereRepository ;
    @Autowired
    MoyenneService moyenneService;

    @PostMapping("/ajoutermatiere")
    @ResponseBody
    public Matiere ajoutermatiere(@RequestBody Matiere matiere) {
        return  matiereservice.ajoutermatiere(matiere); // Call instance method on injected service

    }
    @GetMapping("/affichermatieres")
    @ResponseBody
    public List<Matiere> getMatieres() {
        List<Matiere> list = matiereservice.afficherMatieres();
        return list;
    }
    @GetMapping("/affichermatiere/{matiereId}")
    @ResponseBody
    public Matiere affichermatiere(@PathVariable("matiereId") Long id) {
        return matiereservice.affichermatiere(id);
    }
    @DeleteMapping("/supprimermatiere/{matiereId}")
    @ResponseBody
    public void supprimermatiere(@PathVariable("matiereId") Long id) {
        matiereservice.supprimermatiere(id);
    }
    @PutMapping("/modifiermatiere")
    @ResponseBody
    public Matiere modifiermatiere(@RequestBody Matiere matiere) {

        return matiereservice.modifiermatiere(matiere);
    }
    @PostMapping("/{matiereId}/coefficient/{coefficientId}")
    public ResponseEntity<String> affecterCoefficientAMatiere(@PathVariable Long matiereId, @PathVariable Long coefficientId) {
        matiereservice.affecterCoefficientAMatiere(matiereId, coefficientId);
        return ResponseEntity.ok("Coefficient affecté à la matière avec succès");
    }
    @GetMapping("/moyenne-generale")
    public ResponseEntity<Double> calculerMoyenneGenerale() {
        double moyenneGenerale = matiereservice.calculerMoyenneGenerale();
        return ResponseEntity.ok(moyenneGenerale);
    }
}
