package org.youcode.maska_hunters_league.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youcode.maska_hunters_league.domain.entities.Species;
import org.youcode.maska_hunters_league.service.SpeciesService;
import org.youcode.maska_hunters_league.web.VMs.SpeciesVMs.SpeciesVM;
import org.youcode.maska_hunters_league.web.VMs.mapper.SpeciesVMMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/species")
@AllArgsConstructor
@Validated
@PreAuthorize("hasRole('ADMIN')")
public class SpeciesController {

    private final SpeciesService speciesService;
    private final SpeciesVMMapper speciesVMMapper;

    @PostMapping
    public ResponseEntity<Species> createSpecies(@RequestBody @Valid SpeciesVM speciesVM) {
        Species species = speciesVMMapper.toSpecies(speciesVM);
        Species createdSpecies = speciesService.createSpecies(species);
        return ResponseEntity.ok(createdSpecies);
    }

    @GetMapping
    public ResponseEntity<Page<Species>> getAllSpecies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<Species> species = speciesService.getAllSpecies(page,size);
        return ResponseEntity.ok(species);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable UUID id) {
        speciesService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "species is deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Species> updateSpecies(
            @PathVariable UUID id,
            @RequestBody Species speciesUpdates) {

        Species updatedSpecies = speciesService.updateSpecies(id, speciesUpdates);
        return ResponseEntity.ok(updatedSpecies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Species> getSpeciesById(@PathVariable UUID id) {
        Species species = speciesService.findById(id);
        return ResponseEntity.ok(species);
    }
}
