package tn.com.sigrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.com.sigrh.models.Note;
import tn.com.sigrh.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/ajouternote")
    @ResponseBody
    public Note ajouternote(@RequestBody Note note) {
        return  noteService.ajouternote(note); // Call instance method on injected service

    }
    @GetMapping("/affichernotes")
    @ResponseBody
    public List<Note> getNotes() {
        List<Note> list = noteService.affichernotes();
        return list;
    }
    @GetMapping("/affichernote/{noteId}")
    @ResponseBody
    public Note affichernpte(@PathVariable("noteId") Long id) {

        return noteService.affichernote(id);
    }
    @DeleteMapping("/supprimernote/{noteId}")
    @ResponseBody
    public void supprimernote(@PathVariable("noteId") Long id) {

        noteService.supprimernote(id);
    }
    @PutMapping("/modifiernote")
    @ResponseBody
    public Note modifiernote(@RequestBody Note note) {

        return noteService.modifiernote(note);
    }


}
