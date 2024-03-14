package org.taohansen.tvplaylist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taohansen.tvplaylist.entities.Epg;

@Repository
public interface EpgRepository extends JpaRepository<Epg, Long> {
}