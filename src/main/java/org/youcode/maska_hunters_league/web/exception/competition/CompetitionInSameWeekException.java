package org.youcode.maska_hunters_league.web.exception.competition;

public class CompetitionInSameWeekException extends RuntimeException {
    public CompetitionInSameWeekException() {
        super("A competition already exists in the same week.");
    }
}
