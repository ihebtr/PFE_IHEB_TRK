package tn.com.sigrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.com.sigrh.models.Classe;
import tn.com.sigrh.models.Matiere;
import tn.com.sigrh.models.Moyenne;
import tn.com.sigrh.repo.MatiereRepo;
import tn.com.sigrh.service.MoyenneService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/moyenne")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MoyenneController {
    MoyenneService moyenneService;

    MatiereRepo matiereRepository;
    public MoyenneController(MoyenneService moyenneService, MatiereRepo matiereRepository) {
        this.moyenneService = moyenneService;
        this.matiereRepository = matiereRepository;
    }
    @GetMapping
    public List<Moyenne> getAllMoyennes() {
        List<Moyenne> list = moyenneService.afficherMoyennes();
        return list;
    }

    @GetMapping("/affichermoyenne/{moyenneId}")
    @ResponseBody
    public Moyenne affichermoyenne(@PathVariable("moyenneId") Long id) {
        return moyenneService.affichermoyenne(id);
    }

    @PostMapping("/ajoutermoyenne")
    @ResponseBody
    public Moyenne createMoyenne(@RequestBody Moyenne moyenne) {
        return moyenneService.ajoutermoyenne(moyenne);
    }

    @PutMapping("/modifiermoyenne")
    @ResponseBody
    public Moyenne updateMoyenne(@PathVariable Long id, @RequestBody Moyenne moyenne) {
        return moyenneService.modifiermoyenne(moyenne);
    }

    @DeleteMapping("/supprimermoyenne/{moyenneId}")
    @ResponseBody
    public void deleteMoyenne(@PathVariable Long id) {
        moyenneService.supprimermoyenne(id);
    }
    @GetMapping("/matiere/{matiereId}")
    @ResponseBody
    public ResponseEntity<Double> calculerMoyenneMatiere(@PathVariable Long matiereId) {
        Optional<Matiere> matiereOptional = matiereRepository.findById(matiereId);
        if (matiereOptional.isPresent()) {
            Matiere matiere = matiereOptional.get();
            double moyenne = moyenneService.calculerMoyenneMatiere(matiere);
            return ResponseEntity.ok(moyenne);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
