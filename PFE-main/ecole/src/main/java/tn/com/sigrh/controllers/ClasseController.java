package tn.com.sigrh.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.com.sigrh.models.Classe;
import tn.com.sigrh.service.ClasseServiceI;


import java.util.List;

@RestController
@RequestMapping("/api/classe")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClasseController {
    @Autowired
    ClasseServiceI classeservice;
    @Operation(summary = "Ajouter une classe", description = "Ajoute une nouvelle classe.")
    @PostMapping("/ajouterclasse")
    @ResponseBody
    public Classe ajouterclasse(@RequestBody Classe classe) {
        return  classeservice.ajouterclasse(classe); // Call instance method on injected service

    }
    @Operation(summary = "Afficher toutes les classes", description = "Récupère la liste de toutes les classes.")
    @GetMapping("/afficherclasses")
    @ResponseBody
    public List<Classe> getClasses() {
        List<Classe> list = classeservice.afficherclasses();
        return list;
    }
    @Operation(summary = "Afficher une classe par ID", description = "Récupère une classe par son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classe trouvée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Classe.class)) }),
            @ApiResponse(responseCode = "404", description = "Classe non trouvée")
    })
    @GetMapping("/afficherclasse/{classeId}")
    @ResponseBody
    public Classe afficherclasse(@PathVariable("classeId") Long id) {

        return classeservice.afficherclasse(id);
    }
    @DeleteMapping("/supprimerclasse/{classeId}")
    @ResponseBody
    public void supprimerclasse(@PathVariable("classeId") Long id) {
        classeservice.supprimerclasse(id);
    }
    @PutMapping("/modifierclasse")
    @ResponseBody
    public Classe modifierclasse(@RequestBody Classe classe) {

        return classeservice.modifierclasse(classe);
    }
    @PutMapping("/{classeId}/assign/{niveauId}")
    public void affecterClasseANiveau(@PathVariable Long classeId, @PathVariable Long niveauId) {
        classeservice.affecterClasseANiveau(classeId, niveauId);
    }
}
