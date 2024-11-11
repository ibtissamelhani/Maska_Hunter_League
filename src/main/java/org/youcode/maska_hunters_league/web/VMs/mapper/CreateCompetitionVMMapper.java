package org.youcode.maska_hunters_league.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.youcode.maska_hunters_league.domain.entities.Competition;
import org.youcode.maska_hunters_league.web.VMs.CreateCompetitionVM;

@Mapper(componentModel = "spring")
public interface CreateCompetitionVMMapper {
    Competition toCompetition(CreateCompetitionVM createCompetitionVM);
    CreateCompetitionVM toCreateCompetitionVM(Competition competition);
}
