package org.youcode.maska_hunters_league.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.youcode.maska_hunters_league.domain.entities.Participation;
import org.youcode.maska_hunters_league.web.VMs.ParticipationVMs.ParticipationVM;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "competition.code", target = "competitionCode")
    @Mapping(expression = "java(participation.getHunts() != null ? participation.getHunts().size() : 0)", target = "numberOfHunts")
    ParticipationVM toParticipationVM(Participation participation);
}
