package tn.com.sigrh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.sigrh.models.Classe;
import tn.com.sigrh.models.Matiere;
import tn.com.sigrh.models.Niveau;
import tn.com.sigrh.models.Note;
import tn.com.sigrh.repo.MatiereRepo;
import tn.com.sigrh.repo.NoteRepo;

import java.util.ArrayList;
import java.util.List;
@Service
public class NoteService implements NoteServiceI{
    @Autowired
    private NoteRepo noterepo;
    @Autowired
    private MatiereRepo matiererepo;


    @Override
    public List<Note> affichernotes() {
        List<Note> Notes = new ArrayList<>();
        noterepo.findAll().forEach(e -> Notes.add(e));
        return Notes;
    }

    @Override
    public Note affichernote(Long id) {
        Note note = noterepo.findById(id).get();
        return note;
    }

    @Override
    public Note ajouternote(Note note) {
        noterepo.save(note);
        return note;
    }

    @Override
    public Note modifiernote(Note note) {
        noterepo.save(note) ;
        return note;
    }

    @Override
    public void supprimernote(Long id) {
        Note note = noterepo.findById(id).get();
        noterepo.delete(note);
    }

}
