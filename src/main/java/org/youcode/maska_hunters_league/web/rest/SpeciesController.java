package org.youcode.maska_hunters_league.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.maska_hunters_league.domain.entities.Species;
import org.youcode.maska_hunters_league.service.SpeciesService;

@RestController
@RequestMapping("v1/api/species")
@AllArgsConstructor
public class SpeciesController {

    private final SpeciesService speciesService;

    @PostMapping
    public ResponseEntity<Species> createSpecies(@RequestBody Species species) {
        Species createdSpecies = speciesService.createSpecies(species);
        return ResponseEntity.ok(createdSpecies);
    }
}
