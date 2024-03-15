package org.taohansen.tvplaylist.controllers;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taohansen.tvplaylist.entities.Channel;
import org.taohansen.tvplaylist.entities.Country;
import org.taohansen.tvplaylist.entities.Language;
import org.taohansen.tvplaylist.services.*;

import java.io.ByteArrayInputStream;
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

    @GetMapping(value = "/export", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> generateM3uPlaylist() {
        List<Channel> channels = channelService.getAllChannels();
        ByteArrayInputStream m3uContent = channelService.generateM3uPlaylist(channels);

        InputStreamResource resource = new InputStreamResource(m3uContent);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=channels.m3u");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(m3uContent.available())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}