package org.youcode.maska_hunters_league.web.VMs;

import jakarta.validation.constraints.Min;
import lombok.*;
import org.youcode.maska_hunters_league.domain.enums.SpeciesType;
import org.youcode.maska_hunters_league.validation.EnumValue;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCompetitionVM {

    private String location;

//    @Future(message = "date must be in the future")
    private LocalDateTime date;

    @EnumValue(enumClass = SpeciesType.class , message = "invalid species Type")
    private String speciesType;

    @Min(1)
    private Integer minParticipants;

    @Min(1)
    private Integer maxParticipants;

    private Boolean openRegistration;
}
