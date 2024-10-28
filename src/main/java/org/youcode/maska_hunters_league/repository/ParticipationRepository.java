package org.youcode.maska_hunters_league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.maska_hunters_league.domain.entities.Participation;

import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
}
