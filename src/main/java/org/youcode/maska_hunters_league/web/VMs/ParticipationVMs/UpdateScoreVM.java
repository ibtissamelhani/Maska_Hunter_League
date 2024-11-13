package org.youcode.maska_hunters_league.web.VMs.ParticipationVMs;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateScoreVM {
    @NotNull(message = "participation id is required")
    private UUID participationId;
    
    @NotNull(message = "score is required")
    private Double score;
}
