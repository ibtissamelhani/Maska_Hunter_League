package org.youcode.maska_hunters_league.service;

import org.youcode.maska_hunters_league.domain.entities.Participation;
import org.youcode.maska_hunters_league.service.DTOs.ParticipationResultDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParticipationService {
    Participation registerUserToCompetition(UUID userId, UUID competitionId);
    Participation findById(UUID id);
    void updateParticipationScore(Participation participation);
    List<ParticipationResultDTO> getUserResults(UUID userId);
}
