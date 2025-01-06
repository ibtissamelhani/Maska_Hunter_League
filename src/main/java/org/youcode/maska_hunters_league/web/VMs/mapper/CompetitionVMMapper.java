package org.youcode.maska_hunters_league.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.youcode.maska_hunters_league.domain.entities.Competition;
import org.youcode.maska_hunters_league.web.VMs.CompetitionVMs.CompetitionVM;

@Mapper(componentModel = "spring")
public interface CompetitionVMMapper {
    Competition toCompetition(CompetitionVM competitionVM);

    @Mapping(expression = "java(competition.getParticipations() != null ? competition.getParticipations().size() : 0)", target = "numberOfParticipants")
    CompetitionVM toCompetitionVM(Competition competition);
}
