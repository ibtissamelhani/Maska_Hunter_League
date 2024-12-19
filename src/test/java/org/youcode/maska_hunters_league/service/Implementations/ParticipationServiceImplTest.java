package org.youcode.maska_hunters_league.service.Implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.youcode.maska_hunters_league.domain.entities.*;
import org.youcode.maska_hunters_league.domain.enums.Difficulty;
import org.youcode.maska_hunters_league.domain.enums.SpeciesType;
import org.youcode.maska_hunters_league.repository.ParticipationRepository;
import org.youcode.maska_hunters_league.service.CompetitionService;
import org.youcode.maska_hunters_league.service.DTOs.ParticipationResultDTO;
import org.youcode.maska_hunters_league.service.DTOs.PodiumDTO;
import org.youcode.maska_hunters_league.service.UserService;
import org.youcode.maska_hunters_league.web.exception.InvalidCredentialsException;
import org.youcode.maska_hunters_league.web.exception.competition.RegistrationClosedException;
import org.youcode.maska_hunters_league.web.exception.participation.ParticipationAlreadyExistException;
import org.youcode.maska_hunters_league.web.exception.participation.ParticipationNotFoundException;
import org.youcode.maska_hunters_league.web.exception.user.LicenseExpiredException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ParticipationServiceImplTest {

    @Mock
    private ParticipationRepository participationRepository;

    @Mock
    private UserService userService;

    @Mock
    private CompetitionService competitionService;

    @InjectMocks
    private ParticipationServiceImpl participationService;

    private User mockUser;
    private Competition mockCompetition;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser = User.builder()
                .id(UUID.randomUUID())
                .licenseExpirationDate(LocalDateTime.now().plusDays(1))
                .build();

        mockCompetition = Competition.builder()
                .id(UUID.randomUUID())
                .openRegistration(true)
                .build();
    }

    @Test
    void registerUserToCompetition_successfulRegistration() {
        when(competitionService.findById(mockCompetition.getId())).thenReturn(mockCompetition);
        when(userService.findById(mockUser.getId())).thenReturn(mockUser);
        when(participationRepository.existsByUserAndCompetition(mockUser, mockCompetition)).thenReturn(false);

        Participation savedParticipation = Participation.builder()
                .user(mockUser)
                .competition(mockCompetition)
                .score(0.0)
                .build();

        when(participationRepository.save(any(Participation.class))).thenReturn(savedParticipation);

        Participation result = participationService.registerUserToCompetition(mockUser.getId(), mockCompetition.getId());

        assertNotNull(result);
        assertEquals(mockUser, result.getUser());
        assertEquals(mockCompetition, result.getCompetition());
        assertEquals(0.0, result.getScore());
    }

    @Test
    void registerUserToCompetition_registrationClosed() {
        mockCompetition.setOpenRegistration(false);
        when(competitionService.findById(mockCompetition.getId())).thenReturn(mockCompetition);
        when(userService.findById(mockUser.getId())).thenReturn(mockUser);

        assertThrows(RegistrationClosedException.class, () ->
                participationService.registerUserToCompetition(mockUser.getId(), mockCompetition.getId()));
    }

    @Test
    void registerUserToCompetition_licenseExpired() {
        mockUser.setLicenseExpirationDate(LocalDateTime.now().minusDays(1));
        when(competitionService.findById(mockCompetition.getId())).thenReturn(mockCompetition);
        when(userService.findById(mockUser.getId())).thenReturn(mockUser);

        assertThrows(LicenseExpiredException.class, () ->
                participationService.registerUserToCompetition(mockUser.getId(), mockCompetition.getId()));
    }

    @Test
    void registerUserToCompetition_alreadyRegistered() {
        when(competitionService.findById(mockCompetition.getId())).thenReturn(mockCompetition);
        when(userService.findById(mockUser.getId())).thenReturn(mockUser);
        when(participationRepository.existsByUserAndCompetition(mockUser, mockCompetition)).thenReturn(true);

        assertThrows(ParticipationAlreadyExistException.class, () ->
                participationService.registerUserToCompetition(mockUser.getId(), mockCompetition.getId()));
    }

    @Test
    void findById_participationFound() {
        UUID participationId = UUID.randomUUID();
        Participation participation = Participation.builder().id(participationId).build();

        when(participationRepository.findById(participationId)).thenReturn(Optional.of(participation));

        Participation result = participationService.findById(participationId);

        assertNotNull(result);
        assertEquals(participationId, result.getId());
    }

    @Test
    void findById_participationNotFound() {
        UUID participationId = UUID.randomUUID();

        when(participationRepository.findById(participationId)).thenReturn(Optional.empty());

        assertThrows(ParticipationNotFoundException.class, () ->
                participationService.findById(participationId));
    }

    @Test
    void updateParticipationScore_calculatesCorrectScore() {
        Species species = Species.builder()
                .points(10)
                .category(SpeciesType.BIRD)
                .difficulty(Difficulty.RARE)
                .build();

        Hunt hunt = Hunt.builder().species(species).weight(2.0).build();

        Participation participation = Participation.builder()
                .hunts(List.of(hunt))
                .build();

        when(participationRepository.save(participation)).thenReturn(participation);

        participationService.updateParticipationScore(participation);

        double expectedScore = (10.0 + 2.0) * SpeciesType.BIRD.getValue() * Difficulty.RARE.getValue();
        assertEquals(expectedScore, participation.getScore());
    }

    @Test
    void getUserResults_userIdIsNull() {
        assertThrows(InvalidCredentialsException.class, () ->
                participationService.getUserResults(null));
    }

    @Test
    void getTopThreeParticipants_competitionIdIsNull() {
        assertThrows(InvalidCredentialsException.class, () ->
                participationService.getTopThreeParticipants(null));
    }

    @Test
    void getTopThreeParticipants_returnsPodium() {
        PodiumDTO first = new PodiumDTO("User1", 100.0);
        PodiumDTO second = new PodiumDTO("User2", 80.0);
        PodiumDTO third = new PodiumDTO("User3", 60.0);

        when(participationRepository.findTopThreeByCompetition(mockCompetition.getId()))
                .thenReturn(List.of(first, second, third));

        List<PodiumDTO> podium = participationService.getTopThreeParticipants(mockCompetition.getId());

        assertEquals(3, podium.size());
        assertEquals("User1", podium.get(0).getUsername());
    }
}