package org.youcode.maska_hunters_league.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youcode.maska_hunters_league.domain.entities.Participation;
import org.youcode.maska_hunters_league.service.ParticipationService;
import org.youcode.maska_hunters_league.web.VMs.ParticipationVMs.ParticipationRequestVM;

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
}
