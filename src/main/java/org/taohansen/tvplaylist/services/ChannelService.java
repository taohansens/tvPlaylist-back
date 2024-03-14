package org.taohansen.tvplaylist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taohansen.tvplaylist.entities.*;
import org.taohansen.tvplaylist.repositories.ChannelRepository;
import org.taohansen.tvplaylist.services.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    public List<Channel> findByCountry(Country country) {
        return channelRepository.findByCountry(country);
    }

    public List<Channel> findByLanguage(Language language) {
        return channelRepository.findByLanguage(language);
    }

    public List<Channel> findByEpg(Epg epg) {
        return channelRepository.findByEpg(epg);
    }

    public Channel getChannelById(Long id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Channel not found with id " + id));
    }

    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public Channel updateChannel(Long id, Channel channelDetails) {
        Channel channel = getChannelById(id);
        return channelRepository.save(channel);
    }

    public void deleteChannel(Long id) {
        Channel channel = getChannelById(id);
        channelRepository.delete(channel);
    }
}