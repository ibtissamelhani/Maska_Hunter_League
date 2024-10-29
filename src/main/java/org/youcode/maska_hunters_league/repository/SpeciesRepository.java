package org.youcode.maska_hunters_league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.maska_hunters_league.domain.entities.Species;

import java.util.UUID;

public interface SpeciesRepository extends JpaRepository<Species, UUID> {
}
