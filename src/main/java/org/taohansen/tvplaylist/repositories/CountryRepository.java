package org.taohansen.tvplaylist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taohansen.tvplaylist.entities.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}