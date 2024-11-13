package org.youcode.maska_hunters_league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.maska_hunters_league.domain.entities.Competition;
import org.youcode.maska_hunters_league.domain.entities.Participation;
import org.youcode.maska_hunters_league.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {

    boolean existsByUserAndCompetition(User user, Competition competition);
    List<Participation> findByUserId(UUID userId);
    Optional<Participation> findByUserIdAndCompetitionId(UUID userId, UUID competitionId);

}
