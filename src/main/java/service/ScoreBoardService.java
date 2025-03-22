package service;

import model.Match;
import org.apache.commons.lang3.StringUtils;
import util.SequenceGenerator;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardService {


    private final List<Match> scoreboard;
    private final SequenceGenerator sequenceGenerator;

    public ScoreBoardService() {
        this.scoreboard = new ArrayList<>();
        sequenceGenerator = new SequenceGenerator();
    }

    public Match addNewMatch(String homeTeam, String awayTeam)
    {
        if (StringUtils.isBlank(homeTeam) || StringUtils.isBlank(awayTeam))
        {
            throw new IllegalArgumentException("team names cannot be empty");
        }

        Match newMatch = new Match(sequenceGenerator.generate(), homeTeam,awayTeam);

        scoreboard.add(newMatch);
        return newMatch;
    }

    public void finishMatch(int matchId)
    {
        if(!scoreboard.removeIf(x-> x.getId() == matchId))
        {
            throw new IllegalArgumentException("Could not complete action, match does not exist in the scoreboard");
        }
    }

    public Match updateScore(int matchId, int homeTeamScore, int awayTeamScore)
    {
        for(Match match : scoreboard)
        {
            if (match.getId() == matchId)
            {
                match.updateScore(homeTeamScore,awayTeamScore);
                return match;
            }
        }
        return null;
    }

}
