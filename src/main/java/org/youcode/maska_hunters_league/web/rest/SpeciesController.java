package org.youcode.maska_hunters_league.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.maska_hunters_league.domain.entities.Species;
import org.youcode.maska_hunters_league.service.SpeciesService;

import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<Page<Species>> getAllSpecies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<Species> species = speciesService.getAllSpecies(page,size);
        return ResponseEntity.ok(species);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> delete(@PathVariable UUID id){
        speciesService.delete(id);
        return ResponseEntity.ok("species is deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Species> updateSpecies(
            @PathVariable UUID id,
            @RequestBody Species speciesUpdates) {

        Species updatedSpecies = speciesService.updateSpecies(id, speciesUpdates);
        return ResponseEntity.ok(updatedSpecies);
    }
}
