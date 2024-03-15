package org.taohansen.tvplaylist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taohansen.tvplaylist.entities.*;
import org.taohansen.tvplaylist.repositories.ChannelRepository;
import org.taohansen.tvplaylist.services.exceptions.ResourceNotFoundException;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
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
        return channelRepository.findByEpgXml(epg);
    }

    public Channel getChannelById(Long id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Channel not found with id " + id));
    }

    public Channel createChannel(Channel channel, Country country, Language language, Category category){
        channel.setCountry(country);
        channel.setLanguage(language);
        channel.setCategory(category);
        channel.setDateOfSubmission(LocalDateTime.now());
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

    public ByteArrayInputStream generateM3uPlaylist(List<Channel> channels) {
        StringBuilder m3uContent = new StringBuilder("#EXTM3U\n");

        for (Channel channel : channels) {
            m3uContent.append("#EXTINF:-1 tvg-id=\"")
                    .append(channel.getTvgId())
                    .append("\" tvg-logo=\"")
                    .append(channel.getTvgLogo())
                    .append("\" group-title=\"")
                    .append(channel.getCategory())
                    .append("\",")
                    .append(channel.getName())
                    .append("\n");
            m3uContent.append(channel.getUrl()).append("\n");
        }

        return new ByteArrayInputStream(m3uContent.toString().getBytes(StandardCharsets.UTF_8));
    }
}