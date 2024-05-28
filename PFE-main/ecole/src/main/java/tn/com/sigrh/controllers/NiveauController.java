package tn.com.sigrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.com.sigrh.models.Niveau;
import tn.com.sigrh.service.NiveauServiceI;

import java.util.List;

@RestController
@RequestMapping("/api/niveau")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NiveauController {
   @Autowired
   NiveauServiceI  niveauService;
    @PostMapping("/ajouterniveau")
    @ResponseBody
    public Niveau ajouterniveau(@RequestBody Niveau niveau) {
        return  niveauService.ajouterNiveau(niveau); // Call instance method on injected service

    }
    @GetMapping("/afficherniveaux")
    @ResponseBody
    public List<Niveau> getNiveaux() {
        List<Niveau> list = niveauService.afficherniveaux();
        return list;
    }
    @GetMapping("/afficherniveau/{niveauId}")
    @ResponseBody
    public Niveau afficherNiveau(@PathVariable("niveauId") Long id) {
        return niveauService.afficherniveau(id);
    }
    @DeleteMapping("/supprimerniveau/{niveauId}")
    @ResponseBody
    public void supprimerniveau(@PathVariable("niveauId") Long id) {
        niveauService.supprimerniveau(id);
    }
    @PutMapping("/modifierniveau")
    @ResponseBody
    public Niveau modifierniveau(@RequestBody Niveau n) {
        return niveauService.modifierniveau(n);
    }


}

/*
@RestController
@RequestMapping("/api/matiere")
public class MatiereController {

    private final MatiereService matiereService;

    @Autowired
    public MatiereController(MatiereService matiereService) {
        this.matiereService = matiereService;
    }

    @PostMapping("/ajoutermatiere")
    @ResponseBody
    public Matiere addSubject(@RequestBody Matiere m) {
        Matiere savedMatiere = matiereService.addmatiere(m); // Call instance method on injected service
        return savedMatiere;
    }
 */