package org.taohansen.tvplaylist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taohansen.tvplaylist.entities.Epg;
import org.taohansen.tvplaylist.repositories.EpgRepository;
import org.taohansen.tvplaylist.services.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class EpgService {

    @Autowired
    private EpgRepository epgRepository;

    public List<Epg> getAllEpgs() {
        return epgRepository.findAll();
    }
    public Epg getEpgById(Long id) {
        return epgRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Epg not found with id " + id));
    }
    public Epg createEpg(Epg Epg) {
        return epgRepository.save(Epg);
    }
    public void deleteEpg(Long id) {
        Epg Epg = getEpgById(id);
        epgRepository.delete(Epg);
    }
}
