package tn.com.sigrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.com.sigrh.models.Coefficient;
import tn.com.sigrh.service.CoefficientService;

@RestController
@RequestMapping("/api/coefficients")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CoefficientController {
    @Autowired
    private CoefficientService coefficientService;

    @PostMapping("/ajoutercoefficient")
    public ResponseEntity<Coefficient> createCoefficient(@RequestBody Coefficient coefficient) {
        Coefficient savedCoefficient = coefficientService.saveCoefficient(coefficient);
        return new ResponseEntity<>(savedCoefficient, HttpStatus.CREATED);
    }

    @GetMapping("/affichercoefficient/{id}")
    public ResponseEntity<Coefficient> getCoefficientById(@PathVariable Long id) {
        Coefficient coefficient = coefficientService.getCoefficientById(id);
        if (coefficient != null) {
            return new ResponseEntity<>(coefficient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Ajoutez d'autres méthodes de contrôleur pour les fonctionnalités CRUD et personnalisées
}
