package org.youcode.maska_hunters_league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.youcode.maska_hunters_league.domain.entities.Competition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

    Optional<Competition> findByCode(String code);

//    @Query("SELECT c FROM Competition c WHERE EXTRACT(YEAR FROM c.date) = EXTRACT(YEAR FROM CAST(:date AS timestamp)) " +
//            "AND EXTRACT(WEEK FROM c.date) = EXTRACT(WEEK FROM CAST(:date AS timestamp))")
//    List<Competition> findCompetitionsInSameWeek(@Param("date") LocalDateTime date);

}
