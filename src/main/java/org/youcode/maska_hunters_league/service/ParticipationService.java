package org.youcode.maska_hunters_league.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.maska_hunters_league.domain.entities.Participation;
import org.youcode.maska_hunters_league.service.DTOs.ParticipationResultDTO;
import org.youcode.maska_hunters_league.service.DTOs.PodiumDTO;

import java.util.List;
import java.util.UUID;

public interface ParticipationService {
    Participation registerUserToCompetition(UUID userId, UUID competitionId);
    Participation findById(UUID id);
    void updateParticipationScore(Participation participation);
    List<ParticipationResultDTO> getUserResults(UUID userId);
    ParticipationResultDTO getUserCompetitionResult(UUID userId, UUID competitionId);
    List<PodiumDTO> getTopThreeParticipants(UUID competitionId);
    Page<Participation> getPaginatedParticipationsByCompetition(UUID competitionId, Pageable pageable);
}
