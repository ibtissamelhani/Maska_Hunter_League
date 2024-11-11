package org.youcode.maska_hunters_league.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.youcode.maska_hunters_league.domain.entities.Competition;
import org.youcode.maska_hunters_league.web.VMs.CompetitionVMs.UpdateCompetitionVM;

@Mapper(componentModel = "spring")
public interface UpdateCompetitionVMMapper {
    Competition toCompetition(UpdateCompetitionVM updateCompetitionVM);
    UpdateCompetitionVM toUpdateCompetitionVM(Competition competition);
}
