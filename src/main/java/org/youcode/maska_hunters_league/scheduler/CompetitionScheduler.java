package org.youcode.maska_hunters_league.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.youcode.maska_hunters_league.service.CompetitionService;

@Component
public class CompetitionScheduler {

    private final CompetitionService competitionService;

    public CompetitionScheduler(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void closeRegistrationsBeforeCompetition() {
        competitionService.closeRegistrationsBeforeCompetition();
    }
}
