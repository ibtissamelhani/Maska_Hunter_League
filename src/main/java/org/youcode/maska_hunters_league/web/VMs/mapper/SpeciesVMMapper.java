package org.youcode.maska_hunters_league.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.youcode.maska_hunters_league.domain.entities.Species;
import org.youcode.maska_hunters_league.web.VMs.SpeciesVM;

@Mapper(componentModel = "spring")
public interface SpeciesVMMapper {

    Species toSpecies(SpeciesVM speciesVM);
    SpeciesVM toSpeciesVM(Species species);
}
