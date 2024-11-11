package org.youcode.maska_hunters_league.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.maska_hunters_league.domain.entities.Competition;
import org.youcode.maska_hunters_league.domain.entities.Participation;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.repository.ParticipationRepository;
import org.youcode.maska_hunters_league.service.CompetitionService;
import org.youcode.maska_hunters_league.service.ParticipationService;
import org.youcode.maska_hunters_league.service.UserService;
import org.youcode.maska_hunters_league.web.exception.competition.RegistrationClosedException;
import org.youcode.maska_hunters_league.web.exception.participation.ParticipationAlreadyExistException;
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
}
