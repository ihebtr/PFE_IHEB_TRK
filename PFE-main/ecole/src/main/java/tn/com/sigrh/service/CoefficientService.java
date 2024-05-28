package tn.com.sigrh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.sigrh.models.Coefficient;
import tn.com.sigrh.repo.CoefficientRepo;

@Service
public class CoefficientService {
    @Autowired
    private CoefficientRepo coefficientRepository;

    // Méthodes pour accéder aux fonctionnalités CRUD

    public Coefficient saveCoefficient(Coefficient coefficient) {
        return coefficientRepository.save(coefficient);
    }

    public Coefficient getCoefficientById(Long id) {
        return coefficientRepository.findById(id).orElse(null);
    }


}
