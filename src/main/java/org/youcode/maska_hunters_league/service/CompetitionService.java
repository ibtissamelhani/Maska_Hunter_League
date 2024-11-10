package org.youcode.maska_hunters_league.service;

import org.youcode.maska_hunters_league.domain.entities.Competition;

import java.util.Optional;
import java.util.UUID;

public interface CompetitionService {

    Competition createCompetition(Competition competition);
    Optional<Competition> findByCode(String code);
    Competition findById(UUID id);
}
