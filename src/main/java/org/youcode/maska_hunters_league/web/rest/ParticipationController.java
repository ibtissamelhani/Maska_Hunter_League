package org.youcode.maska_hunters_league.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youcode.maska_hunters_league.domain.entities.Participation;
import org.youcode.maska_hunters_league.service.DTOs.ParticipationResultDTO;
import org.youcode.maska_hunters_league.service.DTOs.PodiumDTO;
import org.youcode.maska_hunters_league.service.ParticipationService;
import org.youcode.maska_hunters_league.web.VMs.ParticipationVMs.ParticipationRequestVM;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/participation")
@AllArgsConstructor
@Validated
public class ParticipationController {

    private final ParticipationService participationService;

    @PostMapping
    public ResponseEntity<Participation> registerUserToCompetition(@RequestBody @Valid ParticipationRequestVM participationRequestVM){
        Participation participation = participationService.registerUserToCompetition(participationRequestVM.getUserId(),participationRequestVM.getCompetitionId());
        return ResponseEntity.ok(participation);
    }

    @GetMapping("/results")
    public ResponseEntity<List<ParticipationResultDTO>> getUserAllCompetitionsResults(@RequestParam UUID userId) {
        List<ParticipationResultDTO> results = participationService.getUserResults(userId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/competition-result")
    public ResponseEntity<ParticipationResultDTO> getUserSingleCompetitionResult(
            @RequestParam UUID userId, @RequestParam UUID competitionId) {

        ParticipationResultDTO result = participationService.getUserCompetitionResult(userId, competitionId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/podium")
    public ResponseEntity<List<PodiumDTO>> getTopThreeParticipants(@RequestParam UUID competitionId){
        List<PodiumDTO> podium = participationService.getTopThreeParticipants(competitionId);
        return ResponseEntity.ok(podium);
    }
}
