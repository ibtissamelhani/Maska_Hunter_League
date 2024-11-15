package org.youcode.maska_hunters_league.web.VMs.CompetitionVMs;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.youcode.maska_hunters_league.domain.enums.SpeciesType;
import org.youcode.maska_hunters_league.validation.EnumValue;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCompetitionVM {

    @NotBlank(message = "location is required")
    private String location;

    @NotNull(message = "date is required")
    @Future(message = "date must be in the future")
    private LocalDateTime date;

    @NotNull(message = "species Type is required")
    @EnumValue(enumClass = SpeciesType.class , message = "invalid species Type")
    private String speciesType;

    @NotNull(message = "minParticipants is required")
    @Min(1)
    private Integer minParticipants;

    @NotNull@NotNull(message = "maxParticipants is required")
    @Min(1)
    private Integer maxParticipants;

    private Boolean openRegistration = true;
}
