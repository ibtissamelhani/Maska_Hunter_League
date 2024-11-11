package org.youcode.maska_hunters_league.web.VMs;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.youcode.maska_hunters_league.domain.enums.SpeciesType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionVM {
    private UUID id;

    private String code;

    private String location;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private SpeciesType speciesType;

    private Integer minParticipants;

    private Integer maxParticipants;

    private Boolean openRegistration;
}
