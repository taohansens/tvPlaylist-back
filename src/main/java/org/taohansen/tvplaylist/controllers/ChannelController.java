package org.taohansen.tvplaylist.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taohansen.tvplaylist.entities.Channel;
import org.taohansen.tvplaylist.entities.Country;
import org.taohansen.tvplaylist.entities.Language;
import org.taohansen.tvplaylist.services.*;

import java.util.List;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {

    @Autowired
    private ChannelService channelService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private LanguageService languageService;

    @GetMapping
    public List<Channel> getAllChannels() {
        return channelService.getAllChannels();
    }

    @GetMapping("/{id}")
    public Channel getChannelById(@PathVariable Long id) {
        return channelService.getChannelById(id);
    }

    @GetMapping("/country/{countryId}")
    public List<Channel> getChannelsByCountry(@PathVariable Long countryId) {
        Country country = countryService.getCountryById(countryId);
        return channelService.findByCountry(country);
    }
    @GetMapping("/language/{languageId}")
    public List<Channel> getChannelsByLanguage(@PathVariable Long languageId) {
        Language language = languageService.getLanguageById(languageId);
        return channelService.findByLanguage(language);
    }

    @PostMapping
    public ResponseEntity<Channel> createChannel(@RequestBody @Valid Channel channel,
                                                 @RequestParam Long countryId,
                                                 @RequestParam Long languageId) {
        Country country = countryService.getCountryById(countryId);
        Language language = languageService.getLanguageById(languageId);
        Channel createdChannel = channelService.createChannel(channel, country, language);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChannel);
    }
    @PutMapping("/{id}")
    public Channel updateChannel(@PathVariable Long id, @RequestBody Channel channelDetails) {
        return channelService.updateChannel(id, channelDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteChannel(@PathVariable Long id) {
        channelService.deleteChannel(id);
    }
}