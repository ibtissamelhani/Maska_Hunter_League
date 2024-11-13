package org.youcode.maska_hunters_league.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.maska_hunters_league.domain.entities.Hunt;
import org.youcode.maska_hunters_league.domain.entities.Participation;
import org.youcode.maska_hunters_league.domain.entities.Species;
import org.youcode.maska_hunters_league.repository.HuntRepository;
import org.youcode.maska_hunters_league.service.DTOs.HuntRequestDTO;
import org.youcode.maska_hunters_league.service.HuntService;
import org.youcode.maska_hunters_league.service.ParticipationService;
import org.youcode.maska_hunters_league.service.SpeciesService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class HuntServiceImpl implements HuntService {

    private final HuntRepository huntRepository;
    private final ParticipationService participationService;
    private final SpeciesService speciesService;

    @Transactional
    @Override
    public Hunt createHunt(HuntRequestDTO huntRequestDTO) {

        Participation participation = participationService.findById(huntRequestDTO.getParticipationId());
        Species species = speciesService.findById(huntRequestDTO.getSpeciesId());
        Hunt hunt = Hunt.builder()
                .weight(huntRequestDTO.getWeight())
                .species(species)
                .participation(participation)
                .build();

        Hunt savedHunt = huntRepository.save(hunt);
        participationService.updateParticipationScore(participation);
        return savedHunt;
    }
}

