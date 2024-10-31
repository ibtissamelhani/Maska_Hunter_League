package org.youcode.maska_hunters_league.service;

import org.springframework.data.domain.Page;
import org.youcode.maska_hunters_league.domain.entities.Species;


public interface SpeciesService {
    Species createSpecies(Species species);
    Page<Species> getAllSpecies(int page, int size);
}
