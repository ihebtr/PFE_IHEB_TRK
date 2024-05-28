package tn.com.sigrh.service;

import tn.com.sigrh.models.Niveau;
import tn.com.sigrh.models.Note;

import java.util.List;

public interface NoteServiceI {
    List<Note> affichernotes();

    Note affichernote(Long id);

    Note ajouternote(Note note);

    Note modifiernote(Note note);

    void supprimernote(Long id);


}
