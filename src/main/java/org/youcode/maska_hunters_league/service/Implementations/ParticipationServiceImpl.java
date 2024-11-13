package org.youcode.maska_hunters_league.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.maska_hunters_league.domain.entities.*;
import org.youcode.maska_hunters_league.domain.enums.Difficulty;
import org.youcode.maska_hunters_league.domain.enums.SpeciesType;
import org.youcode.maska_hunters_league.repository.ParticipationRepository;
import org.youcode.maska_hunters_league.service.CompetitionService;
import org.youcode.maska_hunters_league.service.ParticipationService;
import org.youcode.maska_hunters_league.service.UserService;
import org.youcode.maska_hunters_league.web.exception.competition.RegistrationClosedException;
import org.youcode.maska_hunters_league.web.exception.participation.ParticipationAlreadyExistException;
import org.youcode.maska_hunters_league.web.exception.participation.ParticipationNotFoundException;
import org.youcode.maska_hunters_league.web.exception.user.LicenseExpiredException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepository;
    private final UserService userService;
    private final CompetitionService competitionService;


    @Override
    public Participation registerUserToCompetition(UUID userId, UUID competitionId) {

        Competition competition = competitionService.findById(competitionId);
        User user = userService.findById(userId);

        if (Boolean.FALSE.equals(competition.getOpenRegistration())) {
            throw new RegistrationClosedException("Registration for this competition is closed.");
        }

        if (user.getLicenseExpirationDate() != null && user.getLicenseExpirationDate().isBefore(LocalDateTime.now())) {
            throw new LicenseExpiredException("User's license has expired and cannot participate.");
        }


        boolean isAlreadyRegistered = participationRepository.existsByUserAndCompetition(user,competition);
        if (isAlreadyRegistered){
            throw new ParticipationAlreadyExistException("User is already registered for this competition.");
        }

        Participation participation = Participation.builder()
                .user(user)
                .competition(competition)
                .score(0.)
                .build();

        return participationRepository.save(participation);
    }

    @Override
    public Participation findById(UUID id) {
        return participationRepository.findById(id)
                .orElseThrow(()->new ParticipationNotFoundException("participation Not found"));
    }

    @Override
    public void updateParticipationScore(Participation participation) {
        double score = 0.0;

        for (Hunt hunt : participation.getHunts()) {
            Species species = hunt.getSpecies();
            SpeciesType speciesType = species.getCategory();
            Difficulty difficulty = species.getDifficulty();

            score += (species.getPoints() + hunt.getWeight()) * speciesType.getValue() * difficulty.getValue();
        }

        participation.setScore(score);
        participationRepository.save(participation);
    }
}
