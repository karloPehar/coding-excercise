package service;

import model.Match;

public class ScoreBoardService {


    public Match addNewMatch(String homeTeam, String awayTeam)
    {
        Match newMatch = new Match(1,homeTeam,awayTeam);

        return newMatch;
    }
}
