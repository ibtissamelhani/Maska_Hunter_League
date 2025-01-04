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
import org.youcode.maska_hunters_league.web.VMs.ParticipationVMs.ParticipationVM;
import org.youcode.maska_hunters_league.web.VMs.mapper.ParticipationMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/participation")
@AllArgsConstructor
@Validated
public class ParticipationController {

    private final ParticipationService participationService;
    private final ParticipationMapper participationMapper;

    @PostMapping
    public ResponseEntity<ParticipationVM> registerUserToCompetition(@RequestBody @Valid ParticipationRequestVM participationRequestVM){
        Participation participation = participationService.registerUserToCompetition(participationRequestVM.getUserId(),participationRequestVM.getCompetitionId());
        ParticipationVM participationVM = participationMapper.toParticipationVM(participation);
        return ResponseEntity.ok(participationVM);
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
