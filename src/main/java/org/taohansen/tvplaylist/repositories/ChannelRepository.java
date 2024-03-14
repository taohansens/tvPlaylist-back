package org.taohansen.tvplaylist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taohansen.tvplaylist.entities.Channel;
import org.taohansen.tvplaylist.entities.Country;
import org.taohansen.tvplaylist.entities.Epg;
import org.taohansen.tvplaylist.entities.Language;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
    List<Channel> findByCountry(Country country);

    List<Channel> findByLanguage(Language language);
    List<Channel> findByEpg(Epg epg);
    List<Channel> findByCountryAndLanguage(Country country, Language language);
}