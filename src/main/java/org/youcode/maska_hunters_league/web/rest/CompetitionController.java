package org.youcode.maska_hunters_league.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youcode.maska_hunters_league.domain.entities.Competition;
import org.youcode.maska_hunters_league.service.CompetitionService;
import org.youcode.maska_hunters_league.web.VMs.CreateCompetitionVM;
import org.youcode.maska_hunters_league.web.VMs.mapper.CreateCompetitionVMMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/competition")
@Validated
@AllArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;
    private final CreateCompetitionVMMapper createCompetitionVMMapper;

    @GetMapping
    public ResponseEntity<Page<Competition>> getAllCompetitions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Competition> competitions = competitionService.findAllCompetitionsPaginated(page,size);
        return ResponseEntity.ok(competitions);
    }

    @PostMapping()
    public ResponseEntity<Competition> createCompetition(@Valid @RequestBody CreateCompetitionVM createCompetitionVM) {
        Competition competition = createCompetitionVMMapper.toCompetition(createCompetitionVM);
        Competition savedCompetition = competitionService.createCompetition(competition);
        return new ResponseEntity<>(savedCompetition, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competition> getCompetitionDetails(@PathVariable UUID id) {
        Competition competition = competitionService.findById(id);
        return ResponseEntity.ok(competition);
    }
}
