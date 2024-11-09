package org.youcode.maska_hunters_league.service.Implementations;

import org.springframework.stereotype.Service;
import org.youcode.maska_hunters_league.domain.entities.Competition;
import org.youcode.maska_hunters_league.repository.CompetitionRepository;
import org.youcode.maska_hunters_league.service.CompetitionService;
import org.youcode.maska_hunters_league.utils.DateUtils;
import org.youcode.maska_hunters_league.web.exception.InvalidCredentialsException;
import org.youcode.maska_hunters_league.web.exception.competition.CompetitionAlreadyExistException;
import org.youcode.maska_hunters_league.web.exception.competition.CompetitionInSameWeekException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Competition createCompetition(Competition competition) {
        validateCompetition(competition);
        String code = generateCompetitionCode(competition.getLocation(),competition.getDate());
        competitionRepository.findByCode(code)
                .ifPresent(c -> {throw new CompetitionAlreadyExistException("competition already exist");
                });
        competition.setCode(code);
        return competitionRepository.save(competition);
    }

    private void validateCompetition(Competition competition){
        if (competition.getMinParticipants() >= competition.getMaxParticipants()) {
            throw new InvalidCredentialsException("The minimum number of participants must be less than the maximum.");
        }

    }

    private String generateCompetitionCode(String location, LocalDateTime date) {
        String formattedDate = DateUtils.formatDate(date);
        return location + "_" + formattedDate;
    }
}
