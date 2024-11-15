package org.youcode.maska_hunters_league.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.youcode.maska_hunters_league.domain.entities.Competition;
import org.youcode.maska_hunters_league.web.VMs.CompetitionVMs.CompetitionVM;

@Mapper(componentModel = "spring")
public interface CompetitionVMMapper {
    Competition toCompetition(CompetitionVM competitionVM);
    CompetitionVM toCompetitionVM(Competition competition);
}
