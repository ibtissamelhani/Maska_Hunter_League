package org.youcode.maska_hunters_league.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.youcode.maska_hunters_league.domain.entities.Competition;
import org.youcode.maska_hunters_league.domain.entities.Participation;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.service.DTOs.PodiumDTO;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {

    boolean existsByUserAndCompetition(User user, Competition competition);
    List<Participation> findByUserId(UUID userId);
    Optional<Participation> findByUserIdAndCompetitionId(UUID userId, UUID competitionId);

    @Query("SELECT new org.youcode.maska_hunters_league.service.DTOs.PodiumDTO(p.user.username, p.score) " +
            "FROM Participation p " +
            "WHERE p.competition.id = :competitionId " +
            "ORDER BY p.score DESC LIMIT 3")
    List<PodiumDTO> findTopThreeByCompetition(@Param("competitionId") UUID competitionId);

    @Query("SELECT p FROM Participation p WHERE p.competition.id = :competitionId")
    Page<Participation> findByCompetitionId(@Param("competitionId") UUID competitionId, Pageable pageable);
}
