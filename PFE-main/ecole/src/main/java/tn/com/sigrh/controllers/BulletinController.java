package tn.com.sigrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.com.sigrh.models.Bulletin;
import tn.com.sigrh.models.Classe;
import tn.com.sigrh.service.BulletinService;

import java.util.List;

@RestController
@RequestMapping("/api/bulletin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BulletinController {
    @Autowired
    BulletinService bulletinservice;
    @PostMapping("/ajouterbulletin")
    @ResponseBody
    public Bulletin ajouterbulletin(@RequestBody Bulletin bulletin) {
        return  bulletinservice.ajouterbulletin(bulletin); // Call instance method on injected service

    }
    @GetMapping("/afficherbulletins")
    @ResponseBody
    public List<Bulletin> getBulletins() {
        List<Bulletin> list = bulletinservice.afficherbulletin();
        return list;
    }
    @GetMapping("/afficherbulletin/{bulletinId}")
    @ResponseBody
    public Bulletin afficherbulletin(@PathVariable("bulletinId") Long id) {

        return bulletinservice.afficherbulletin(id);
    }
    @DeleteMapping("/supprimerbulletin/{bulletinId}")
    @ResponseBody
    public void supprimerbulletin(@PathVariable("bulletinId") Long id) {

        bulletinservice.supprimerbulletin(id);
    }
    @PutMapping("/modifierbulletin")
    @ResponseBody
    public Bulletin modifierbulletin(@RequestBody Bulletin bulletin) {

        return bulletinservice.modifierbulletin(bulletin);
    }
}
