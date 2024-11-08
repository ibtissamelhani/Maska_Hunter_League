package org.youcode.maska_hunters_league.web.VMs;


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

    @NotBlank
    private String location;

    @NotNull
    @Future
    private LocalDateTime date;

    @NotNull
    @EnumValue(enumClass = SpeciesType.class , message = "invalid species Type")
    private String speciesType;

    @Min(1)
    private Integer minParticipants;

    @NotNull
    @Min(1)
    private Integer maxParticipants;

    private Boolean openRegistration = false;
}
