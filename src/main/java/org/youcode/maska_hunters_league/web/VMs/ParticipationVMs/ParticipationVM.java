package org.youcode.maska_hunters_league.web.VMs.ParticipationVMs;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationVM {
    private UUID id;
    private String username;
    private String competitionCode;
    private Integer numberOfHunts;
    private Double score;
}
