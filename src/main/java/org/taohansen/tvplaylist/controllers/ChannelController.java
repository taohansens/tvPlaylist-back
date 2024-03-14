package org.taohansen.tvplaylist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

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
    public Channel createChannel(@RequestBody Channel channel) {
        return channelService.createChannel(channel);
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