package org.youcode.maska_hunters_league.service;

import org.springframework.data.domain.Page;
import org.youcode.maska_hunters_league.domain.entities.Competition;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompetitionService {

    Competition createCompetition(Competition competition);
    Optional<Competition> findByCode(String code);
    Competition findById(UUID id);
    Page<Competition> findAllCompetitionsPaginated(int page, int size);
}
