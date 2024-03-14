package org.taohansen.tvplaylist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taohansen.tvplaylist.entities.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}