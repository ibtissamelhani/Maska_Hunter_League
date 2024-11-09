package org.youcode.maska_hunters_league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.maska_hunters_league.domain.entities.Competition;

import java.util.Optional;
import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

    Optional<Competition> findByCode(String code);
}
