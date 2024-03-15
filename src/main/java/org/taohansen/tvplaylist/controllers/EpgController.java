package org.taohansen.tvplaylist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.taohansen.tvplaylist.entities.Epg;
import org.taohansen.tvplaylist.services.EpgService;

import java.util.List;

@RestController
@RequestMapping("/api/epg")
public class EpgController {

    @Autowired
    private EpgService epgService;

    @GetMapping
    public List<Epg> getAllEpg() {
        return epgService.getAllEpgs();
    }

    @GetMapping("/{id}")
    public Epg getEpgId(@PathVariable Long id) {
        return epgService.getEpgById(id);
    }
    
    @PostMapping
    public Epg createEpg(@RequestBody Epg Epg) {
        return epgService.createEpg(Epg);
    }

    @DeleteMapping("/{id}")
    public void deleteEpg(@PathVariable Long id) {
        epgService.deleteEpg(id);
    }
}