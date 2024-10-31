package org.youcode.maska_hunters_league.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.maska_hunters_league.domain.entities.Species;
import org.youcode.maska_hunters_league.repository.SpeciesRepository;
import org.youcode.maska_hunters_league.service.SpeciesService;
import org.youcode.maska_hunters_league.web.exception.species.InvalidSpeciesException;

import java.util.List;

@Service
@AllArgsConstructor
public class SpeciesServiceImpl implements SpeciesService {

    private SpeciesRepository speciesRepository;

    @Override
    public Species createSpecies(Species species) {
        if (species == null) {
            throw new InvalidSpeciesException("Species cannot be null");
        }
        return speciesRepository.save(species);
    }

    @Override
    public Page<Species> getAllSpecies(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return speciesRepository.findAll(pageable);
    }

}
